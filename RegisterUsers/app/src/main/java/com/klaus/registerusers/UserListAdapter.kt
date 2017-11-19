package com.klaus.registerusers

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by chomoonsik on 2017. 11. 18..
 */
/**
 * 메인 Activity 에서 ListView 에 출력할 항목을 정의한다.
 */
data class ViewHolder(val pic:ImageView,
                      val name:TextView,
                      val tel:TextView,
                      val del:ImageButton)

class UserListAdapter(context: Context, cursor: Cursor)
    :CursorAdapter(context, cursor, FLAG_REGISTER_CONTENT_OBSERVER) {

    val mCtx = context
    private var onItemClick: View.OnClickListener? = null
    /**
     * 새로운 View 를 정의해준다.
     */
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val mainView = inflater.inflate(R.layout.user_list, parent, false)
        var holder:ViewHolder = ViewHolder(mainView.findViewById<ImageView>(R.id.profile) as ImageView,
                mainView.findViewById<TextView>(R.id.name) as TextView,
                mainView.findViewById<TextView>(R.id.tel_num) as TextView,
                mainView.findViewById<ImageButton>(R.id.del_item) as ImageButton)
        mainView.tag = holder
        // ListView 가 처리되므로 뷰에서 ClickListener를 넘겨주어야 이벤트가 발생한다.
        mainView.setOnClickListener(onItemClick)
        return mainView
    }

    /**
     * 생성 후에 내부에 들어갈 내용들을 넣어준다.
     */
    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val holder = view?.tag as ViewHolder

        holder.name.text = cursor?.getString(1)
        holder.tel.text = cursor?.getString(3)
        val picture:Drawable = getPicture(cursor!!.getString(4))
                ?: context?.getDrawable(android.R.drawable.ic_menu_gallery)!!
        holder.pic.background = picture
        // cursor id 를 저장
        holder.del.tag = cursor?.getLong(0)
    }

    /**
     * 그림이 있는 경로에서 그림을 가져오도록 한다.
     */
    private fun getPicture(path:String): Drawable? {
        val img_id = path.toLong()
        if(img_id == 0L) return null

        val bitmap:Bitmap = MediaStore.Images.Thumbnails.getThumbnail(mCtx.contentResolver, img_id,
                MediaStore.Images.Thumbnails.MICRO_KIND, null)
        bitmap?:return null
        return BitmapDrawable(mCtx.resources, bitmap)
    }

    /**
     * ListView 의 항목에서 클릭이벤트가 발생할 수 있도록 한다.
     */
    fun setOnItemClickListener(clickItem: View.OnClickListener) {
        onItemClick = clickItem
    }
}