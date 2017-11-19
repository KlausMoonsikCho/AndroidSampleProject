package com.klaus.kotlinproject21

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        var costum1:CustomAdapter = CustomAdapter(this, arrayListOf("data1","data2"))
        var costum2:CustomAdapter = CustomAdapter(this, arrayListOf("data1","data2"),
                View.OnClickListener { Toast.makeText(this,"Call",Toast.LENGTH_SHORT).show() })
    }
}
