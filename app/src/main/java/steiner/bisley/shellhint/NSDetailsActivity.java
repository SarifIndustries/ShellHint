package steiner.bisley.shellhint;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        //HTool tool = HTool.htools[scanID];

        // Database Cursor
        SQLiteOpenHelper dbHelper = new HToolDatabaseHelper(this);
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("TOOLTABLE",
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_ID"},
                    "_id = ?",
                    new String[] {Integer.toString(scanID)},
                    null, null, null);
//            Cursor cursor = db.query("TOOLTABLE",
//                    new String[] {"NAME", "DESCRIPTION", "IMAGE_ID"},
//                    null,
//                    null,
//                    null, null, null);

            // Read from cursor
            if(cursor.moveToFirst()) {
                String toolName = cursor.getString(0);
                String toolDescription = cursor.getString(1);
                int toolImageID = cursor.getInt(2);

                TextView nameView = findViewById(R.id.ScanName);
                nameView.setText(toolName);

                TextView hintView = findViewById(R.id.ScanHint);
                hintView.setText(toolDescription);

                ImageView imageView = findViewById(R.id.ScanImage);
                imageView.setImageResource(toolImageID);
                imageView.setContentDescription(toolName);
            }
            else {
                Toast tos = Toast.makeText(this, "Cursor situation.", Toast.LENGTH_LONG);
                tos.show();
            }

            cursor.close();
            db.close();

        } catch(SQLiteException e) {
            Toast tos = Toast.makeText(this, "Database situation.", Toast.LENGTH_LONG);
            tos.show();
        }


//        TextView nameView = (TextView) findViewById(R.id.ScanName);
//        nameView.setText(tool.getName());
//
//        TextView hintView = (TextView) findViewById(R.id.ScanHint);
//        hintView.setText(tool.getDescription());
//
//        ImageView imageView = (ImageView) findViewById(R.id.ScanImage);
//        imageView.setImageResource(tool.getImageID());
//        imageView.setContentDescription(tool.getName());
    }
}
