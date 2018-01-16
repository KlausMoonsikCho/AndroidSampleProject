package com.klaus.myfragmentapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.klaus.myfragmentapp.fragment.FragmentA;

public class MainActivity extends AppCompatActivity {
    FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        FragmentA fragmentA = new FragmentA();
        fragmentA.setContext(this);
        fragmentTransaction.add(R.id.fragmentViewGroup, fragmentA);
        fragmentTransaction.commit();
    }
}
