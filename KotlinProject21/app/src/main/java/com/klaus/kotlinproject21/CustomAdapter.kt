package com.klaus.kotlinproject21

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import java.util.*

/**
 * Created by chomoonsik on 2017. 10. 21..
 */
class CustomAdapter: BaseAdapter {
    var mContext:Context = null!!
    var mItemArray:ArrayList<String>
    var mListener:View.OnClickListener

    constructor(context:Context, itemArray:ArrayList<String>):super()
    {
        mContext = context;
        mItemArray = itemArray
    }

    constructor(context: Context, itemArray: ArrayList<String>, listener: View.OnClickListener):super()
    {
        mContext = context
        mItemArray = itemArray
        mListener = listener;
    }

    /////////////////////////////
    // BaseAdapter Override 함수
    /////////////////////////////
    override fun getCount(): Int {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(p0: Int): Any {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(p0: Int): Long {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}