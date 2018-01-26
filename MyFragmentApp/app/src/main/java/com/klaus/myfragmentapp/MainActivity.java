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
        // Fragment 를 정의하기 위해서 사용한다.
        mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        FragmentA fragmentA = new FragmentA();
        fragmentA.setContext(this);
        fragmentTransaction.add(R.id.fragmentViewGroup, fragmentA);
        // Fragment 에서 정의한 옵션을 정의하기 위해서 반드시 아래 commit 함수를 사용해야한다.
        fragmentTransaction.commit();
    }
}
