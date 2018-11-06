package steiner.bisley.shellhint;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class NSDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "steiner.extraid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nsdetails);

        // Assign new toolbar
        Toolbar toolb = findViewById(R.id.toolbar);
        setSupportActionBar(toolb);

        // Deploy Home button on toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int scanID = (Integer) getIntent().getExtras().get(EXTRA_ID);
        HTool tool = HTool.htools[scanID];

        TextView nameView = (TextView) findViewById(R.id.ScanName);
        nameView.setText(tool.getName());

        TextView hintView = (TextView) findViewById(R.id.ScanHint);
        hintView.setText(tool.getDescription());

        ImageView imageView = (ImageView) findViewById(R.id.ScanImage);
        imageView.setImageResource(tool.getImageID());
        imageView.setContentDescription(tool.getName());
    }
}
