package com.klaus.registerusers.common

/**
 * Created by chomoonsik on 2017. 11. 18..
 */
/**
 * 프로젝트 전체에서 사용되는 Static 형태의 변수를 모아놓은 클래스
 */
class CompanionObj {
    companion object {
        // Database
        val DB_Name = "user.db"
        val DB_Version = 1
        // Request Code
        val REQUEST_ADD_USER = 1001
        val PICK_IMAGE = 1010
        val REQ_PERMISSION = 1011
        val REQEST_ANKO_DSL = 1100
        // Intent Flag
        val REQUEST_UPDATE_USER = "UpdateMode"
        val REQUEST_UPDATE_USER_ID = "UpdateUserID"
        val REQUEST_UPDATE_USER_NAME = "UpdateUserName"
        val REQUEST_UPDATE_USER_TELNUM = "UpdateUserTelNum"
        val REQUEST_UPDATE_USER_PIC_PATH = "UpdateUserPicPath"
        // Log Tag
        val TAG = "RegisterUsers"
    }
}