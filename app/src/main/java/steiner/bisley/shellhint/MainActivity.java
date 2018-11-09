package steiner.bisley.shellhint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.ShareActionProvider;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this , "FORENSIC TOOLS coming soon...", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };

        ListView mainList = findViewById(R.id.mainOptionsList);
        mainList.setOnItemClickListener(listener);
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
}
