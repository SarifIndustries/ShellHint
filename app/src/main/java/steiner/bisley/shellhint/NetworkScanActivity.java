package steiner.bisley.shellhint;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class NetworkScanActivity extends AppCompatActivity {

    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_scan);

        // Assign new toolbar
        Toolbar toolb = findViewById(R.id.toolbar);
        setSupportActionBar(toolb);

        // Deploy Home button on toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView listTools = findViewById(R.id.netscanList);

        // Database open
        SQLiteOpenHelper dbHelper = new HToolDatabaseHelper(this);
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("TOOLTABLE",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);

            // Cursor Adapter
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[] {"NAME"},
                    new int[] {android.R.id.text1},
                    0);

            listTools.setAdapter(listAdapter);

        } catch(SQLiteException e) {
            Toast tos = Toast.makeText(this, "Database situation.", Toast.LENGTH_LONG);
            tos.show();
        }


//        ArrayAdapter<HTool> listAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                HTool.htools);

        // listTools.setAdapter(listAdapter);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listScan,
                                    View view,
                                    int position,
                                    long id) {
                Intent int2 = new Intent(NetworkScanActivity.this, NSDetailsActivity.class);
                int2.putExtra(NSDetailsActivity.EXTRA_ID, (int) id);
                startActivity(int2);
            }
        };

        listTools.setOnItemClickListener(listener);
    }


    @Override
    public void onDestroy() {
        if(cursor != null) {
            cursor.close();
        }
        if(db != null) {
            db.close();
        }
        super.onDestroy();
    }
}
