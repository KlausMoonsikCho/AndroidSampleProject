package com.klaus.registerusers.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.klaus.registerusers.common.CompanionObj.Companion.DB_Name
import com.klaus.registerusers.common.CompanionObj.Companion.DB_Version

/**
 * Created by chomoonsik on 2017. 11. 18..
 */

/**
 * 데이터 베이스에 저장할 사용자 정보
 * -> 이름
 * -> 나이
 * -> 전화번호
 * -> 그림 파일 경로
 */
data class UserInfo(val name:String = "No Name",
                    val age:String = "0",
                    val telNum:String = "No TelNum",
                    val pic_path:String)

class DatabaseHandler : SQLiteOpenHelper {
    constructor(context: Context) : super(context, DB_Name, null, DB_Version)

    val TABLE_NAME = "user"
    val ID = "_id"
    val NAME = "name"
    val AGE = "age"
    val TELNUM = "telNum"
    val PIC_PATH = "pic_path"

    val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ${TABLE_NAME} (" +
            "${ID} INTEGER PRIMARY KEY," +
            "${NAME} TEXT," +
            "${AGE} TEXT," +
            "${TELNUM} TEXT," +
            "${PIC_PATH} TEXT" +
            ")"

    /**
     * DB 에 저장되어 있는 정보를 모두 가져온다.
     */
    fun getUserAllWithCursor():Cursor {
        return readableDatabase.query(TABLE_NAME, arrayOf(ID, NAME, AGE, TELNUM, PIC_PATH),
                null, null, null, null, null)
    }

    /**
     * DB 에 사용자 정보를 넣어준다.
     */
    fun addUser(userInfo: UserInfo) {
        var info = ContentValues()
        info.put(NAME, userInfo.name)
        info.put(AGE, userInfo.age)
        info.put(TELNUM, userInfo.telNum)
        info.put(PIC_PATH, userInfo.pic_path)
        writableDatabase.insert(TABLE_NAME, null, info)
    }

    /**
     * ID 값을 이용해서 사용자 정보를 지워준다.
     */
    fun deleteUser(id: Long) {
        var deleteUser = "DELETE FROM ${TABLE_NAME} WHERE ${ID} = ${id};"
        writableDatabase.execSQL(deleteUser)
    }
    //////////////////////////////////////////////////////////
    // SQLiteOpenHelper 사용 시에 사용하는 반드시 Override 할 함수
    //////////////////////////////////////////////////////////
    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}