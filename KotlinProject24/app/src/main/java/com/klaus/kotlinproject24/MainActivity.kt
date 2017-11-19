package com.klaus.kotlinproject24

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.RelativeLayout
import android.widget.Toast
import java.util.jar.Attributes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setLayoutParam(findViewById(R.id.base_view) as LinearLayout)
    }

    private fun setLayoutParam(view: View) {
        var param: LinearLayout.LayoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT).apply{
            gravity = Gravity.CENTER_HORIZONTAL
            topMargin = 50
            leftMargin = 50
        }
        view.layoutParams = param
        Toast.makeText(this, "Success to set View's layoutParams!!", Toast.LENGTH_SHORT).show();
    }
}
