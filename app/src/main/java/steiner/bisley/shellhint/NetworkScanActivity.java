package steiner.bisley.shellhint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NetworkScanActivity extends AppCompatActivity {

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

        ListView listTools = (ListView) findViewById(R.id.netscanList);

        ArrayAdapter<HTool> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                HTool.htools);

        listTools.setAdapter(listAdapter);

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
}
