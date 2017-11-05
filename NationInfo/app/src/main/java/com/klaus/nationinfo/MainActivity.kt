package com.klaus.nationinfo

import android.content.Intent
import android.content.res.TypedArray
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recycleListView = findViewById<RecyclerView>(R.id.nation_list) as RecyclerView
        recycleListView.layoutManager = LinearLayoutManager(this)

        var typeArray: TypedArray = resources.obtainTypedArray(R.array.nation_flag)
        var nationDatas: MutableList<NationData> = mutableListOf()

        for(index: Int in 0..typeArray.length()-1) {
            var resId: Int = typeArray.getResourceId(index, -1)
            if(resId == -1) {
                break;
            }
            var nameResId: Int = R.string.nation_argentina + index
            var capiResId: Int = R.string.capital_argentina + index

            nationDatas.add(NationData(resId, getString(nameResId), getString(capiResId)))
        }

        /*var adapter = NationAdapter(this, listOf(
                NationData(R.drawable.l_flag_korea, "대한민국", "서울"),
                NationData(R.drawable.l_flag_belgium, "벨기에", "브뤼셀"),
                NationData(R.drawable.l_flag_argentina, "아르헨티나", "부에노스아이레스"),
                NationData(R.drawable.l_flag_brazil, "브라질", "브라질리아"),
                NationData(R.drawable.l_flag_canada, "캐나다", "오타와"),
                NationData(R.drawable.l_flag_china, "중국", "베이징")
        ))*/
        var adapter = NationAdapter(this, nationDatas)

        adapter.setOnItemClickListener(this)
        recycleListView.adapter = adapter
    }

    override fun onClick(view: View?) {
        val textView: TextView = view?.findViewById<TextView>(R.id.text_name) as TextView
        val name = textView.text?:"None"
        val intent = Intent(this, NationDetailActivity::class.java)
        intent.putExtra(NationDetailActivity.EXTRA_NATION_NAME, name)
        startActivity(intent)
    }
}
