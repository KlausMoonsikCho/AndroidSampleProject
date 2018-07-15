package com.example.moonsikcho.countdonwtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var countDonwTimer:CountDownTimer? = null

    private var hourValue: Long? = 0
    private var minuteValue: Long? = 0
    private var secondValue: Long? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // init
        initialize()
    }

    /**
     * init to use control
     */
    fun initialize() {
        // init to Button object
        CountDown_Start_Btn!!.setOnClickListener{ v ->
            startCountDonw()
        }

        // init to NumberPicker
        // hour
        hour_picker!!.setMinValue(0)
        hour_picker!!.setMaxValue(23)
        hour_picker!!.setOnValueChangedListener{ numberPicker: NumberPicker, oldVal: Int, newVal: Int ->
            hourValue = newVal.toLong() * 60 * 60
        }
        // minute
        minute_picker!!.setMinValue(0)
        minute_picker!!.setMaxValue(59)
        minute_picker!!.setOnValueChangedListener { numberPicker: NumberPicker, oldVal: Int, newVal: Int ->
            minuteValue = newVal.toLong() * 60

        }
        // second
        second_picker!!.setMinValue(0)
        second_picker!!.setMaxValue(59)
        second_picker!!.setOnValueChangedListener{numberPicker: NumberPicker, oldVal: Int, newVal: Int ->
            secondValue = newVal.toLong()
        }
    }

    /**
     * start to CountDownTimer
     * startSecond : input second
     */
    fun startCountDonw() {
        var startSecond: Long = this!!.hourValue!! + this!!.minuteValue!! + this!!.secondValue!!
        var remainSecond: Long = startSecond
        Toast.makeText(this, "start CountDown!!", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "remainTIme : " + remainSecond + " sec", Toast.LENGTH_SHORT).show()
        // init CountDownTimer Object
        countDonwTimer = object: CountDownTimer(startSecond * 1000, 1000) {
            // check to finish
            override fun onFinish() {
                Toast.makeText(this@MainActivity, "finish CountDown!!", Toast.LENGTH_SHORT).show()
            }
            // check to decrease seconds
            override fun onTick(millisUntilFinished: Long) {
                if (remainSecond != millisUntilFinished) {
                    Toast.makeText(this@MainActivity, "remainTIme : " + (millisUntilFinished/1000) + " sec", Toast.LENGTH_SHORT).show()
                    remainSecond = millisUntilFinished
                }
            }

        }.start() // start CountDownTimer
    }

}
