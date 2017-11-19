package com.klaus.kotlinproject22

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.klaus.calcurator.EvenNumSum

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var evenNumSum: EvenNumSum = EvenNumSum()

        val sumValue1:Int = evenNumSum.getEvenNumSum1()
        val sumValue2:Int = evenNumSum.getEvenNumSum2()

        val evenSumTextView1:TextView = findViewById(R.id.even_num_sum_1) as TextView
        evenSumTextView1.setText("sumValue1 = $sumValue1")

        val evenSumTextView2:TextView = findViewById(R.id.even_num_sum_2) as TextView
        evenSumTextView2.setText("sumValue2 = $sumValue2")

    }
}