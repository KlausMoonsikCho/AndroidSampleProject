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

/**
 * 어플을 실행할 때 가장 처음에 실행되는 부분이다.
 * -> 자바에서 사용하던 extends, implements 대신에 : 으로 상속이나 구현을 정의해준다
 * -> View 내의 OnClickListener 가 있으므로 그 내부 함수인 onClick 함수를 반드시 정의하고, 구현해줘야 한다.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    // 액티비티 생명주기에서 액티비티가 생성될 때 부르는 CallBack 함수로 인스턴스를 정의하거나 변수를 정의해준다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // 상태 저장을 해야하는 경우에 사용한다.
        setContentView(R.layout.activity_main) // 어떤 Layout 을 사용하는지 명시해준다.
        // Layout 에 그려져 있는 RecyclerListView 를 불러온다.
        var recycleListView = findViewById<RecyclerView>(R.id.nation_list) as RecyclerView
        recycleListView.layoutManager = LinearLayoutManager(this)
        // values 에 array.xml 에 있는 nation_flag 라는 array 리소스를 불러온다.
        var typeArray: TypedArray = resources.obtainTypedArray(R.array.nation_flag)
        // 자바와 다르게 수정가능한 List 앞에는 항상 Mutable 이라는 키워드를 붙여준다.
        // 그렇지 않으면, 접근만 가능하고 추가 및 수정할 수 없는 List 가 생성된다.
        var nationDatas: MutableList<NationData> = mutableListOf()
        // array.xml 에 있는 불러온 리소스 배열을 수정 가능한 List 에 넣어준다
        for(index: Int in 0..typeArray.length()-1) {
            var resId: Int = typeArray.getResourceId(index, -1)
            if(resId == -1) {
                break;
            }
            // 국가명과 수도를 나타내는 리소스는 번갈아가면서 있지만, 1씩 증가해주어야 그 다음의 리소스에 접근가능하다.
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
        // ListView 와 동일하게 RecyclerListView 에서도 별도의 Adapter 를 생성해주어야 한다.
        var adapter = NationAdapter(this, nationDatas)

        adapter.setOnItemClickListener(this)
        recycleListView.adapter = adapter
    }

    /**
     * RecyclerListView 에서 클릭이 발생했을 때 동작하는 부분이다.
     * -> NationDetailActivity로 클릭한 국가명을 넘겨준다.
     */
    override fun onClick(view: View?) {
        val textView: TextView = view?.findViewById<TextView>(R.id.text_name) as TextView
        val name = textView.text?:"None"
        val intent = Intent(this, NationDetailActivity::class.java)
        intent.putExtra(NationDetailActivity.EXTRA_NATION_NAME, name)
        startActivity(intent)
    }
}
