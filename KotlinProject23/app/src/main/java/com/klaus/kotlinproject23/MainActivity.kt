package com.klaus.kotlinproject23

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import com.klaus.change.function.ChangeFunction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var changeFunc:ChangeFunction = ChangeFunction(this)

        changeFunc.setLayoutParam1(this.findViewById(R.id.base_view1) as RelativeLayout)
        changeFunc.setLayoutParam2(this.findViewById(R.id.base_view1) as RelativeLayout)
    }
}
