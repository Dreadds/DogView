package com.dreads.dogapp.viewcontrollers.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.dreads.dogapp.R;
import com.dreads.dogapp.viewcontrollers.fragments.FavoritesFragment;
import com.dreads.dogapp.viewcontrollers.fragments.HomeFragment;
import com.dreads.dogapp.viewcontrollers.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            return navigateTo(item);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigateTo(navigation.getMenu().findItem(R.id.navigation_home));
    }

    private Fragment getFragmentFor(MenuItem item){
        switch (item.getItemId()) {
            case R.id.navigation_home: return new HomeFragment();
            case R.id.navigation_favorites: return new FavoritesFragment();
            case R.id.navigation_settings: return new SettingsFragment();
            default: return new HomeFragment();
        }
    }

    private boolean navigateTo(MenuItem item){
        item.setChecked(true);
        return getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, getFragmentFor(item))
                .commit() >0;
    }

}
