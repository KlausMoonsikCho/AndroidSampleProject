package com.klaus.nationinfo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_nation_detail.*
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Created by chomoonsik on 2017. 11. 5..
 */
class NationDetailActivity : AppCompatActivity() {
    companion object {
        val EXTRA_NATION_NAME = "nation_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nation = intent.getStringExtra(EXTRA_NATION_NAME)
        setContentView(R.layout.activity_nation_detail)
        val data:NationDetailData? = getDataFromName(nation)
        img_flag.setImageResource(getResourId(nation))
        initView(data)
    }

    private fun getDataFromName(selected:String):NationDetailData? {
        val gson: Gson = GsonBuilder().create()
        val inputStream:InputStream = assets.open("nation_data.json")
        val reader:InputStreamReader = InputStreamReader(inputStream)
        val detailData = gson.fromJson<GsonData>(reader, GsonData::class.java)

        for(data in detailData.data) {
            if(selected.equals(data.name)) {
                return data;
            }
        }
        return null
    }

    private fun getResourId(selected: String) :Int {
        var resourId = 0;
        when(selected) {
            getString(R.string.nation_argentina) -> {
                resourId = R.drawable.l_flag_argentina
            }
            getString(R.string.nation_belgium) -> {
                resourId = R.drawable.l_flag_belgium
            }
            getString(R.string.nation_brazil) -> {
                resourId = R.drawable.l_flag_brazil
            }
            getString(R.string.nation_canada) -> {
                resourId = R.drawable.l_flag_canada
            }
            getString(R.string.nation_china) -> {
                resourId = R.drawable.l_flag_china
            }
            getString(R.string.nation_croatia) -> {
                resourId = R.drawable.l_flag_croatia
            }
            getString(R.string.nation_czech) -> {
                resourId = R.drawable.l_flag_czech
            }
            getString(R.string.nation_germany) -> {
                resourId = R.drawable.l_flag_germany
            }
            getString(R.string.nation_ghana) -> {
                resourId = R.drawable.l_flag_ghana
            }
            getString(R.string.nation_greece) -> {
                resourId = R.drawable.l_flag_greece
            }
            getString(R.string.nation_korea) -> {
                resourId = R.drawable.l_flag_korea
            }
            getString(R.string.nation_norway) -> {
                resourId = R.drawable.l_flag_norway
            }
            getString(R.string.nation_united_states) -> {
                resourId = R.drawable.l_flag_united_states
            }
        }
        return resourId
    }

    private fun initView(data:NationDetailData?) {
        txt_name.text = data?.name
        capital.text = data?.capital
        volume.text = data?.volume
        weather.text = data?.weather
        language.text = data?.language
        location.text = data?.location
    }
}