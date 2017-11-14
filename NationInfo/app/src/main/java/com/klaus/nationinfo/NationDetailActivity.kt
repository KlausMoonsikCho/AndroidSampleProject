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
 * 선택된 국가에 대해서 자세한 정보를 JSON 에서 가져와서 출력해주는 액티비티이다.
 */
class NationDetailActivity : AppCompatActivity() {
    // 어느 클래스에서든 모두 접근가능한 변수로 아래와 같이 정의해준다.
    companion object {
        val EXTRA_NATION_NAME = "nation_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 인텐트로 국가명을 받아온다.
        val nation = intent.getStringExtra(EXTRA_NATION_NAME)
        setContentView(R.layout.activity_nation_detail) // 어떤 Layout 에서 그려지는 명시해준다.
        val data:NationDetailData? = getDataFromName(nation) // JSON 에서 자세한 정보를 가져온다.
        img_flag.setImageResource(getResourId(nation)) // 국가명으로 이미지 리소스 ID 를 가져온다.
        initView(data) // 각 뷰를 초기화 해준다.
    }

    /**
     * JSON 에서 데이터를 가지고 올 떄 사용한다.
     */
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

    /**
     * 국가명을 가지고 이미지 리소스 ID 를 리턴해준다.
     */
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

    /**
     * 각 뷰를 NationDetailData 를 이용해서 초기화 해준다.
     */
    private fun initView(data:NationDetailData?) {
        txt_name.text = data?.name
        capital.text = data?.capital
        volume.text = data?.volume
        weather.text = data?.weather
        language.text = data?.language
        location.text = data?.location
    }
}