package com.klaus.kotlinproject32

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var textView : TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById<TextView>(R.id.result_sum) as TextView

        var carculate:CalculateInterface = Calculate(this)
        var sumData = carculate.sumLange(1, 50)
        textView!!.setText(carculate.attributeNum(sumData))
    }
}
