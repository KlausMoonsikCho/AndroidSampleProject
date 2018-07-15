package com.example.moonsikcho.stopwatchtest

import android.os.*
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var run_stop_mode_flag:Boolean = false
    private var init_flag:Boolean = false
    private var cur_time:Long = SystemClock.elapsedRealtime()

    private var timerHandler: StopWatchHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initionalize()
    }

    /**
     * init to use control
     */
    fun initionalize() {
        // run & stop Button
        run_stop_btn.text = "start"
        run_stop_btn.setOnClickListener { v ->
            Toast.makeText(this@MainActivity, "start_and_stop", Toast.LENGTH_SHORT).show()
            pressRun_Stop_Button()
        }
        // init Button
        init_btn.text = "init"
        init_btn.setOnClickListener { v ->
            Toast.makeText(this@MainActivity, "init", Toast.LENGTH_SHORT).show()
            pressInit_Button()
        }
        // show cur Time
        show_cur_time.text = "00:00.00"

        timerHandler = StopWatchHandler()
    }

    /**
     * execute to stopwatch
     */
    fun pressRun_Stop_Button() {
        // record to start time
        if (init_flag) {
            cur_time = SystemClock.elapsedRealtime()
            init_flag = false
            run_stop_mode_flag = false
        }
        // change to mode
        if (run_stop_mode_flag) {
            run_stop_btn.text = "start"
            timerHandler!!.removeMessages(0)
        } else {
            run_stop_btn.text = "stop"
            timerHandler!!.sendEmptyMessage(0)
        }

        run_stop_mode_flag = !run_stop_mode_flag
    }


    /**
     * init to stopwatch
     */
    fun pressInit_Button() {
        timerHandler!!.removeMessages(0)

        init_flag = true
        run_stop_btn.text = "start"
        show_cur_time.text = "00:00.00"
    }

    /**
     * update to time
     */
    fun updateCurTime() {
        show_cur_time.text = printCurTime()
    }

    /**
     * print to time
     */
    fun printCurTime(): String {
        var now:Long = SystemClock.elapsedRealtime()
        var stopTime: Long = now - cur_time
        var minute: String = String.format("%d", stopTime/1000/60)
        if (minute.length < 2) minute =  "0"+minute
        var second: String = String.format("%d", stopTime/1000%60)
        if (second.length < 2) second = "0"+second
        var milsecond = String.format("%2d", stopTime%1000/10)
        return (minute+":"+second+"."+milsecond)
    }

    /**
     * change to time
     */
    inner class StopWatchHandler: Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            updateCurTime()

            timerHandler!!.sendEmptyMessage(0)
        }
    }
}
