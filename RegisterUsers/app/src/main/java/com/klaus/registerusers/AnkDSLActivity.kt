package com.klaus.registerusers

import android.app.Activity
import android.os.Bundle
import com.klaus.registerusers.R
import org.jetbrains.anko.button
import org.jetbrains.anko.editText
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import org.jetbrains.anko.verticalLayout

/**
 * Created by chomoonsik on 2017. 11. 18..
 */
/**
 * layout 의 anko_test.xml 파일과 동일한 형태이다.
 * Anko DSL(Domain specific Language) 로 Layout 도 구성할 수 있다는 것을 보여주는 예제이다.
 */
class AnkDSLActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            val name = editText{
                hint = getString(R.string.input_name)
                textSize = 20f
            }

            button (getString(R.string.show_btn)) {
                onClick { toast("안녕하세요 ${name.text}님") }
            }
            // 연습문제 1번으로 인해서 추가된 부분
            button (getString(R.string.exit_btn)) {
                onClick { finish() }
            }
        }
    }
}