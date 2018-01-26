package com.klaus.myfragmentapp.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
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

/**
 * Fragment Life Cycle
 * 1. Fragment is added
 * 2. onAttatch()
 * 3. onCreate()
 * 4. onCreateView()
 * 5. onActivityCreated()
 * 6. onStart()
 * 7. onResume()
 * 8. Fragment is active
 * 9. onPause()
 * 10. onStop()
 * 11. onDestroyView()
 * 12. onDestroy()
 * 13. onDetach()
 * 14. Fragment is destroyed
 * => 11번에서 재 생성 시에 4번에서 다시 실행가능하다.
 */
public class FragmentA extends Fragment {
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
        // ViewGroup 을 생성하는 부분이며 여기에 Fragment 를 정의한 xml 파일을 적용한다.
        // 마지막 파라미터는 레이아웃이 두번째 ViewGroup 에 첨부되야하는지를 나타내는 부울 값인데 시스템이 이미 container
        // 내에 삽입하고 있기 때문에 true 로 전달하면, 최종 레이아웃에 중복된 ViewGroup 을 생성되기 때문입니다.
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Button ClickEvent 의 경우 onCreateView 를 마친 이후에 설정하도록 한다.
        // 그렇지 않으면, View 가 생성되기 전이여서 찾을 수가 없다.
        fragmentButton = (Button) getActivity().findViewById(R.id.fragmentButton);
        fragmentButton.setText(getResources().getText(R.string.fragment_btn));
        fragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.fragmentButton:
                    {
                        Toast.makeText(mContext, "Press Button", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        FragmentB fragmentB = new FragmentB();
                        fragmentB.setContext(mContext);
                        fragmentTransaction.replace(R.id.fragmentViewGroup, fragmentB);
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
