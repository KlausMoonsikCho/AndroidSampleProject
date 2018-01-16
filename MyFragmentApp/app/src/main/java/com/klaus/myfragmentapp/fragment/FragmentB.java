package com.klaus.myfragmentapp.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.klaus.myfragmentapp.R;

/**
 * Created by chomoonsik on 2018. 1. 14..
 */

public class FragmentB extends Fragment {
    public static final String TAG = "FragmentA";

    private Context mContext;
    private Button fragmentButton;

    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Button ClickEvent 의 경우 onCreateView 를 마친 이후에 설정하도록 한다.
        // 그렇지 않으면, View 가 생성되기 전이여서 찾을 수가 없다.
        fragmentButton = (Button) getActivity().findViewById(R.id.fragmentButton);
        fragmentButton.setText(getResources().getText(R.string.fragmentb_btn));
        fragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.fragmentButton:
                    {
                        Toast.makeText(mContext, "Press Button", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        FragmentA fragmentA = new FragmentA();
                        fragmentA.setContext(mContext);
                        fragmentTransaction.replace(R.id.fragmentViewGroup, fragmentA);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fragmentTransaction.commit();
                    }
                    break;
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
