package steiner.bisley.shellhint;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class ForensicActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forensic);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Deploy Panel Toggle button
        DrawerLayout drawer = findViewById(R.id.forensic_drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.forensic_open_panel, R.string.forensic_close_panel);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Panel Listener Registration
        NavigationView panelView = findViewById(R.id.forensic_panelView);
        panelView.setNavigationItemSelectedListener(this);


        Fragment fragment = new ForensicInboxFragment();
        FragmentTransaction transac1 = getSupportFragmentManager().beginTransaction();
        transac1.add(R.id.forensic_content_framelayout, fragment);
        transac1.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;

        switch(id) {
            case R.id.forensic_panel_drafts:
                fragment = new ForensicDraftsFragment();
                break;
            case R.id.forensic_panel_sent_cases:
                fragment = new ForensicSentCasesFragment();
                break;
            case R.id.forensic_panel_closed_cases:
                fragment = new ForensicClosedCasesFragment();
                break;
            case R.id.forensic_panel_help:
                intent = new Intent(this, ForensicHelpActivity.class);
                break;
            case R.id.forensic_panel_feedback:
                intent = new Intent(this, ForensicFeedbackActivity.class);
                break;
            default:
                fragment = new ForensicInboxFragment();
        }

        if(fragment != null) {
            FragmentTransaction transac1 = getSupportFragmentManager().beginTransaction();
            transac1.replace(R.id.forensic_content_framelayout, fragment);
            transac1.commit();
        }
        else if(intent != null) {
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.forensic_drawerLayout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.forensic_drawerLayout);
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}
