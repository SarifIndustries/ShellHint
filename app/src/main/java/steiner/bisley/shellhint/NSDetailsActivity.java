package steiner.bisley.shellhint;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
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
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_ID", "FAVORITE"},
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
                boolean isFavorite = (cursor.getInt(3) == 1);

                TextView nameView = findViewById(R.id.ScanName);
                nameView.setText(toolName);

                TextView hintView = findViewById(R.id.ScanHint);
                hintView.setText(toolDescription);

                ImageView imageView = findViewById(R.id.ScanImage);
                imageView.setImageResource(toolImageID);
                imageView.setContentDescription(toolName);

                CheckBox cb = findViewById(R.id.checkboxFav);
                cb.setChecked(isFavorite);
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

    public void onPorFavor(View view) {
        int toolID = (Integer) getIntent().getExtras().get(EXTRA_ID);
        new UpdateDatabaseAsync().execute(toolID);
    }

    private class UpdateDatabaseAsync extends AsyncTask<Integer, Void, Boolean> {

        private ContentValues contentValues;

        @Override
        protected void onPreExecute() {
            CheckBox cb = findViewById(R.id.checkboxFav);
            boolean inFavor = cb.isChecked();

            contentValues = new ContentValues();
            contentValues.put("FAVORITE", inFavor);
        }

        @Override
        protected Boolean doInBackground(Integer... tools) {
            int toolID = tools[0];

            // Database write
            try {
                SQLiteOpenHelper dbHelper = new HToolDatabaseHelper(NSDetailsActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                db.update("TOOLTABLE", contentValues, "_id = ?", new String[] {Integer.toString(toolID)});

                db.close();
                return true;

            } catch(SQLiteException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(!success) {
                Toast tos = Toast.makeText(NSDetailsActivity.this, "Database situation on async update.", Toast.LENGTH_LONG);
                tos.show();
            }
        }
    }

}
