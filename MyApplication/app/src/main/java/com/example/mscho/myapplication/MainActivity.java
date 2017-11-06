package com.example.mscho.myapplication;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ACTIVITY_TEST";
    private static final String CREATE_COUNT = "Create_Count";
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "Call onCreate");
        if(savedInstanceState != null) {
            count = savedInstanceState.getInt(CREATE_COUNT, 0);
        }
        count++;
        Log.e(TAG, "create count = " + count);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "Call onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "Call onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "Call onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "Call onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "Call onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "Call onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.e(TAG, "Call onSaveInstanceState");
        if(savedInstanceState != null) {
            savedInstanceState.putInt(CREATE_COUNT, count);
        }
    }
}
