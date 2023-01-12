package com.gopi.nycschools;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gopi.nycschools.ui.schoollist.SchoolListActivity;

public class SplashActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, SchoolListActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}