package steiner.bisley.shellhint;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class PlusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);

        // Assign new toolbar
        Toolbar toolb = findViewById(R.id.toolbar);
        setSupportActionBar(toolb);

        // Deploy Home button on toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickFAB(View view) {
        CharSequence text = "Thank you for contribution!";
        int duration = Snackbar.LENGTH_SHORT;
        Snackbar snack = Snackbar.make(findViewById(R.id.PlusCoordinatorID), text, duration);
        snack.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast tos = Toast.makeText(PlusActivity.this, "No problem.", Toast.LENGTH_SHORT);
                tos.show();
            }
        });
        snack.show();
    }
}
