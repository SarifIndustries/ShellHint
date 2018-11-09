package steiner.bisley.shellhint;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ShellCommandsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shell_commands);

        // Assign new toolbar
        Toolbar toolb = findViewById(R.id.toolbar);
        setSupportActionBar(toolb);

        // Deploy Home button on toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // ViewPager deploy adapter
        ShellFragmentPagerAdapter adapSwiper = new ShellFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager pag1 = findViewById(R.id.PagerShell);
        pag1.setAdapter(adapSwiper);

        // Tab Layout Swipe
        TabLayout tablay = findViewById(R.id.ShellSwipeTabLayout);
        tablay.setupWithViewPager(pag1);
    }



    private class ShellFragmentPagerAdapter extends FragmentPagerAdapter {

        public ShellFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new NavigationFragment();
                case 1:
                    return new SystemFragment();
                case 2:
                    return new UtilsFragment();
                case 3:
                    return new NetFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return getResources().getText(R.string.swipe_tab_Navigation);
                case 1:
                    return getResources().getText(R.string.swipe_tab_System);
                case 2:
                    return getResources().getText(R.string.swipe_tab_Utilities);
                case 3:
                    return getResources().getText(R.string.swipe_tab_Network);
            }
            return null;
        }
    }
}
