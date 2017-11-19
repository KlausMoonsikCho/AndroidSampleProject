package com.klaus.kotlinproject32

import android.content.Context

/**
 * Created by chomoonsik on 2017. 11. 16..
 */
class Calculate : CalculateInterface {
    var context:Context ?= null

    constructor(context: Context) {
        this.context = context
    }

    override fun sumLange(startNum: Int, endNum: Int): Int {
        //To change body of created functions use File | Settings | File Templates.
        var sum = 0
        for(number in startNum..endNum) {
            sum += number
        }

        return sum
    }

    override fun attributeNum(checkNum: Int) : String {
        //To change body of created functions use File | Settings | File Templates.
        if(checkNum%2 == 0) {
            return context!!.getString(R.string.even_num)
        } else {
            return context!!.getString(R.string.odd_num)
        }
    }

}