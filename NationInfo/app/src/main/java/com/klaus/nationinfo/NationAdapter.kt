package com.klaus.nationinfo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by chomoonsik on 2017. 10. 31..
 * -> RecyclerListView 에서 사용하기 위한 Adapter 를 만들어준다.
 * -> RecyclerListView 에서 사용하는 Adapter 형태로 잘 알아두도록 해야한다.
 */
/**
 * 국가 정보를 가지고 있는 클래스이다.
 * -> 국기 리소스 ID, 국가명, 수도명
 */
data class NationData(
        var resId:Int,
        var name:String,
        var capital:String
)

/**
 * RecyclerListView 에서 나타낼 출력형태의 View 를 연결해주는 부분이다.
 */
class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val img_flag : ImageView = view.findViewById<ImageView>(R.id.img_flag) as ImageView
    val txt_name: TextView = view.findViewById<TextView>(R.id.text_name) as TextView
    val txt_capital: TextView = view.findViewById<TextView>(R.id.capital) as TextView
}

class NationAdapter (val context: Context, val items:List<NationData>): RecyclerView.Adapter<ViewHolder>() {
    private val mInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var onItemClick: View.OnClickListener? = null

    /**
     * Adapter 에서 사용하기 위한 ViewHolder 만들어 준다
     */
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        // 어떤 Layout 에서 불러오는지 명시해준다.
        val mainView: View = mInflater.inflate(R.layout.layout_nation_list_item, parent, false)
        mainView.setOnClickListener(onItemClick);
        return ViewHolder(mainView)
    }

    /**
     * ViewHolder 를 연결시켜 줍니다.
     */
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.img_flag?.setImageResource(items[position].resId)
        holder?.txt_capital?.text = items[position].capital
        holder?.txt_name?.text = items[position].name
        holder?.txt_name?.tag = position
    }

    /**
     * 정의된 items 의 크기를 리턴합니다.
     */
    override fun getItemCount(): Int = items.size

    /**
     * 클릭 이벤트를 받을 수 있게 설정해줍니다.
     */
    fun setOnItemClickListener(clickItem: View.OnClickListener) {
        onItemClick = clickItem
    }
}