package com.klaus.mysharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 프로그램 개요
 * Preference 를 이해하기 위해서 SharedPreference 를 사용하는 샘플 제작
 * 특정 Key 와 Value 를 매칭해서 저장해서 옵션 저장 및 로드하는 효과를 가져올 수 있는 API 사용 이해
 */
public class MainActivity extends AppCompatActivity {
    private static final String PREF_KEY = "CHANGE_PHONE_CODE";
    private TextView mDisplayText;
    private Button mChagneButton;

    private enum CHANGE_TEXT {
        TEXT_GARAXY,
        TEXT_IPHONE,
        TEXT_DEF
    };
    private CHANGE_TEXT mChange_text_value_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChange_text_value_flag = CHANGE_TEXT.TEXT_DEF;

        mDisplayText = (TextView) findViewById(R.id.test_text);
        mChagneButton = (Button) findViewById(R.id.change_button);
        mChagneButton.setOnClickListener(mChangeButtonClickListener);

        loadSharedPreference();
        displayText();
    }

    private View.OnClickListener mChangeButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                /**
                 * 버튼을 누르면 Text 값을 변경하며, 이를 저장한다.
                 */
                case R.id.change_button:
                {
                    if(mChange_text_value_flag == CHANGE_TEXT.TEXT_GARAXY) {
                        mChange_text_value_flag = CHANGE_TEXT.TEXT_IPHONE;
                        mDisplayText.setText(getResources().getText(R.string.text_value_2));
                        saveSharedPreference(1);
                    } else {
                        mChange_text_value_flag = CHANGE_TEXT.TEXT_GARAXY;
                        mDisplayText.setText(getResources().getText(R.string.text_value_1));
                        saveSharedPreference(0);
                    }
                }
                break;
            }
        }
    };

    /**
     * 특정 Key 에 대한 값을 저장하는 것으로 SharedPreference 를 이용해서 값을 저장한다.
     *
     * @param flag
     */
    private void saveSharedPreference(int flag) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_KEY , flag);
        editor.commit(); // editor.apply(); 과 동일하게 사용가능하다.
    }

    /**
     * 저장한 값을 불러와서 적용한다.
     */
    private void loadSharedPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        int flag = sharedPreferences.getInt(PREF_KEY, -1);
        switch (flag) {
            case 0: mChange_text_value_flag = CHANGE_TEXT.TEXT_GARAXY; break;
            case 1: mChange_text_value_flag = CHANGE_TEXT.TEXT_IPHONE; break;
            default: mChange_text_value_flag = CHANGE_TEXT.TEXT_DEF; break;
        }
    }

    /**
     * 초기화면에서 저장한 값을 이용해서 Text 를 출력해준다.
     */
    private void displayText() {
        if(mChange_text_value_flag == CHANGE_TEXT.TEXT_GARAXY) {
            mDisplayText.setText(getResources().getText(R.string.text_value_1));
        } else if(mChange_text_value_flag == CHANGE_TEXT.TEXT_IPHONE) {
            mDisplayText.setText(getResources().getText(R.string.text_value_2));
        } else {
            mDisplayText.setText(getResources().getText(R.string.text_value_def));
        }
    }
}
