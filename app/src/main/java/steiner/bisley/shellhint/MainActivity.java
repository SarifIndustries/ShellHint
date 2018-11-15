package steiner.bisley.shellhint;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.support.v7.widget.ShareActionProvider;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupMainListView();

        setupFavoritesListView();


    }


    // Assgin menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_prov_action);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareIntentMethod("Request for assistance");
        return super.onCreateOptionsMenu(menu);
    }

    // Toolbar menu actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_plus_action:
                Intent int1 = new Intent(this, PlusActivity.class);
                startActivity(int1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setShareIntentMethod(String text) {
        Intent ints = new Intent(Intent.ACTION_SEND);
        ints.setType("text/plain");
        ints.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(ints);
    }

    private void setupMainListView() {
        // Assgin new toolbar
        Toolbar toolb = findViewById(R.id.toolbar);
        setSupportActionBar(toolb);

        // Create Listener
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Intent int1 = new Intent(MainActivity.this, NetworkScanActivity.class);
                    startActivity(int1);
                }
                if(position == 1) {
                    Intent int2 = new Intent(MainActivity.this, ShellCommandsActivity.class);
                    startActivity(int2);
                }
                if(position == 2) {
                    Intent int3 = new Intent(MainActivity.this, ForensicActivity.class);
                    startActivity(int3);

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(MainActivity.this , "FORENSIC TOOLS coming soon...", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                }
            }
        };

        ListView mainList = findViewById(R.id.mainOptionsList);
        mainList.setOnItemClickListener(listener);

        mainList.getLayoutParams().height = Toolbar.LayoutParams.WRAP_CONTENT;
    }


    private void setupFavoritesListView() {
        ListView favList = findViewById(R.id.mainFavoritesList);

        // Database open
        try {
            SQLiteOpenHelper dbHelper = new HToolDatabaseHelper(this);
            db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("TOOLTABLE",
                    new String[] {"_id", "NAME", "FAVORITE"},
                    "FAVORITE = 1",
                    null, null, null, null);

            CursorAdapter cursorAdapter = new SimpleCursorAdapter(MainActivity.this,
                    R.layout.text_item_fav,
                    cursor,
                    new String[] {"NAME"},
                    new int[] {android.R.id.text1},
                    0);

            favList.setAdapter(cursorAdapter);
            favList.getLayoutParams().height = Toolbar.LayoutParams.WRAP_CONTENT;

        } catch(SQLiteException e) {
            Toast tos = Toast.makeText(this, "Database situation.", Toast.LENGTH_LONG);
            tos.show();
        }

        favList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent int1 = new Intent(MainActivity.this, NSDetailsActivity.class);
                int1.putExtra(NSDetailsActivity.EXTRA_ID, (int) id);
                startActivity(int1);
            }
        });
    }

    // For resetting cursor from database (if favorites changed)
    @Override
    public void onRestart() {
        super.onRestart();

        Cursor newCursor = db.query("TOOLTABLE",
                new String[] {"_id", "NAME", "FAVORITE"},
                "FAVORITE = 1",
                null, null, null, null);
        ListView lv = findViewById(R.id.mainFavoritesList);
        CursorAdapter adapter = (CursorAdapter) lv.getAdapter();
        adapter.changeCursor(newCursor);
        if(cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
    }


    public void onDestroy() {
        super.onDestroy();

        if(cursor != null) {
            cursor.close();
        }
        if(db != null) {
            db.close();
        }
    }
}
