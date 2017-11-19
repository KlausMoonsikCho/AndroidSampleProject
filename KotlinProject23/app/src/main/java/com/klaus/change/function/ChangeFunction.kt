package com.klaus.change.function

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast

/**
 * 같은 동작이지만 다르게 표현하는 함수를 확이한다.
 *
 * Created by chomoonsik on 2017. 10. 24..
 */
class ChangeFunction (val context: Context){
    fun setLayoutParam1(view:View) {
        if(view is LinearLayout) {
            var param:LinearLayout.LayoutParams = view.layoutParams as LinearLayout.LayoutParams
            param.gravity = Gravity.BOTTOM
            view.layoutParams = param
            Toast.makeText(context, "setLayoutParam1's LinearLayout", Toast.LENGTH_SHORT).show()
        } else if (view is RelativeLayout) {
            var param:RelativeLayout.LayoutParams = view.layoutParams as RelativeLayout.LayoutParams
            param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            view.layoutParams = param
            Toast.makeText(context, "setLayoutParam1's RelativeLayout", Toast.LENGTH_SHORT).show()
        }
    }

    fun setLayoutParam2(view:View) {
        when{
            (view is LinearLayout) -> {
                var param:LinearLayout.LayoutParams = view.layoutParams as LinearLayout.LayoutParams
                param.gravity = Gravity.BOTTOM
                view.layoutParams = param
                Toast.makeText(context, "setLayoutParam2's LinearLayout", Toast.LENGTH_SHORT).show()
            }
            (view is RelativeLayout) -> {
                var param:RelativeLayout.LayoutParams = view.layoutParams as RelativeLayout.LayoutParams
                param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                view.layoutParams = param
                Toast.makeText(context, "setLayoutParam2's RelativeLayout", Toast.LENGTH_SHORT).show()
            }
        }
    }
}