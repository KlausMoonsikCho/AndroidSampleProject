package com.klaus.nationinfo

/**
 * Created by chomoonsik on 2017. 11. 5..
 * -> NationDetailActivity 에서 사용해야 할 정보를 저장하는 클래스이다.
 */
class NationDetailData (val name:String,
                        val capital:String,
                        val location:String,
                        val volume:String,
                        val weather:String,
                        val language:String)

/**
 * JSon 에 있는 데이터를 불러오기 위해서 사용한다.
 */
data class GsonData(val data:ArrayList<NationDetailData>)