package com.Course.DesignPatterns;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SensorsTracking sensorsTracking;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constants.REQUEST_CODE);
            return;
        }

        sensorsTracking = new SensorsTracking(this);
        sensorsTracking.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorsTracking.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // To stop the listeners and save battery while the application is in the background
        sensorsTracking.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorsTracking.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorsTracking.pause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void handleClose(View view) {
        sensorsTracking.pause();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
