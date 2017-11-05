package com.klaus.nationinfo

/**
 * Created by chomoonsik on 2017. 11. 5..
 */
class NationDetailData (val name:String,
                        val capital:String,
                        val location:String,
                        val volume:String,
                        val weather:String,
                        val language:String)
data class GsonData(val data:ArrayList<NationDetailData>)