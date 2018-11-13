package steiner.bisley.shellhint;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

class HToolDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "htool";
    private static final int DB_VERSION = 3;

    public HToolDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion);
    }

    private void insertTool(SQLiteDatabase db, String name, String description, int imageID) {
        ContentValues toolValues = new ContentValues();
        toolValues.put("NAME", name);
        toolValues.put("DESCRIPTION", description);
        toolValues.put("IMAGE_ID", imageID);
        db.insert("TOOLTABLE", null, toolValues);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion) {
        if(oldVersion < 1) {
            String SQL_QUERY =
                    "CREATE TABLE TOOLTABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESCRIPTION TEXT, IMAGE_ID INTEGER)";
            db.execSQL(SQL_QUERY);

            insertTool(db, "FPING", "> fping -g 192.168.1.0/24\n" +
                    "> fping -q -a -s -r 1 -g 192.168.0.1-192.168.0.100\n" +
                    "means quiet, show alive, gen stat, retries, ip range", R.drawable.fping);

            insertTool(db, "ARP-SCAN", "> arp-scan --interface=wlan0 --localnet", R.drawable.arpscan);

            insertTool(db, "NMAP", "> nmap -sS -Pn 192.168.1.1\n" +
                    "means Stealth Syn Scan, ignore Ping drop", R.drawable.nmap);

            insertTool(db, "AIRMON-NG", "> airmon-ng check kill\n" +
                    "> airmon-ng start wlan0 [lock-channel]\n" +
                    "> airmon-ng stop wlan0", R.drawable.airmonng);
        }
        if(oldVersion < 2) {
            db.execSQL("ALTER TABLE TOOLTABLE ADD COLUMN FAVORITE NUMERIC;");
        }
        if(oldVersion < 3) {
            db.execSQL("DROP TABLE TOOLTABLE");

            String SQL_QUERY =
                    "CREATE TABLE TOOLTABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESCRIPTION TEXT, IMAGE_ID INTEGER)";
            db.execSQL(SQL_QUERY);

            insertTool(db, "FPING", "> fping -g 192.168.1.0/24\n" +
                    "> fping -q -a -s -r 1 -g 192.168.0.1-192.168.0.100\n" +
                    "means quiet, show alive, gen stat, retries, ip range", R.drawable.fping);

            insertTool(db, "ARP-SCAN", "> arp-scan --interface=wlan0 --localnet", R.drawable.arpscan);

            insertTool(db, "NMAP", "> nmap -sS -Pn 192.168.1.1\n" +
                    "means Stealth Syn Scan, ignore Ping drop", R.drawable.nmap);

            insertTool(db, "AIRMON-NG", "> airmon-ng check kill\n" +
                    "> airmon-ng start wlan0 [lock-channel]\n" +
                    "> airmon-ng stop wlan0", R.drawable.airmonng);

            db.execSQL("ALTER TABLE TOOLTABLE ADD COLUMN FAVORITE NUMERIC;");
        }
    }

}
