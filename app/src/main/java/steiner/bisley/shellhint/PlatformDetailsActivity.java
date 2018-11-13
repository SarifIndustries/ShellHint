package steiner.bisley.shellhint;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class PlatformDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_PLATFORM_ID = "EXTRAPLATFORMID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_details);

        // Deploy home button to toolbar
        Toolbar toolby = findViewById(R.id.toolbar);
        setSupportActionBar(toolby);
//        ActionBar actiony = getSupportActionBar();
//        actiony.setDisplayHomeAsUpEnabled(true);

        // Deploy info
        int platformID = getIntent().getExtras().getInt(EXTRA_PLATFORM_ID);
        String name = PlatformData.platformArray[platformID].getName();
        int imageID = PlatformData.platformArray[platformID].getImageID();
        TextView texty = findViewById(R.id.PDtextView);
        ImageView imagy = findViewById(R.id.PDimage);
        texty.setText(name);
        imagy.setImageDrawable(ContextCompat.getDrawable(this, imageID));
        imagy.setContentDescription(name);
    }
}
