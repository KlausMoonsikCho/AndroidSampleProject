package com.example.moonsikcho.alarmtest

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var alarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialze()
    }

    /**
     * Initialize to control
     */
    fun initialze() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        start_alarm.text = "start_alarm"
        start_alarm.setOnClickListener { v ->
            setAlarm()
        }
    }

    /**
     * set to time
     * -> after 5 seconds
     */
    fun setAlarm() {
        var intent: Intent = Intent(applicationContext, AlarmReceiver::class.java)
        var sendPendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        var date: Date = Date()
        date.time = System.currentTimeMillis() + 5 * 1000
        alarmManager!!.set(AlarmManager.RTC_WAKEUP, date.time, sendPendingIntent)

        Toast.makeText(this, "5초 뒤에 알람", Toast.LENGTH_SHORT).show()

    }

    /**
     * set to AlarmReceiver
     */
    class AlarmReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Alarm!!!!", Toast.LENGTH_SHORT).show()
        }

    }
}
