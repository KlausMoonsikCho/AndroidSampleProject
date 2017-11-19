package com.klaus.registerusers.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.klaus.registerusers.common.CompanionObj.Companion.DB_Name
import com.klaus.registerusers.common.CompanionObj.Companion.DB_Version
import com.klaus.registerusers.common.CompanionObj.Companion.TAG
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.PRIMARY_KEY
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable

/**
 * Created by chomoonsik on 2017. 11. 18..
 */
/**
 * 데이터 베이스에 저장할 사용자 정보로 Anko 라이브러리를 이용한 클래스이다.
 * -> 이름
 * -> 나이
 * -> 전화번호
 * -> 그림 파일 경로
 */
class DatabaseHandler_Anko : SQLiteOpenHelper {
    constructor(context: Context) : super(context, DB_Name, null, DB_Version)
    object UserTable {
        val TABLE_NAME = "user"
        val ID = "_id"
        val NAME = "name"
        val AGE = "age"
        val TELNUM = "telNum"
        val PIC_PATH = "pic_path"
    }

    fun getUserAllWithCursor() : Cursor {
        return readableDatabase.query(UserTable.TABLE_NAME,
                arrayOf(UserTable.ID, UserTable.NAME, UserTable.AGE, UserTable.TELNUM, UserTable.PIC_PATH),
                null, null, null, null, null)
    }

    /**
     * 사용자 정보를 추가해준다.
     */
    fun addUser(userInfo: UserInfo) {
        var info = ContentValues()
        info.put(UserTable.NAME, userInfo.name)
        info.put(UserTable.AGE, userInfo.age)
        info.put(UserTable.TELNUM, userInfo.telNum)
        info.put(UserTable.PIC_PATH, userInfo.pic_path)

        writableDatabase.use {
            writableDatabase.insert(UserTable.TABLE_NAME, null, info)
        }
    }

    /**
     * 사용자 정보를 제거해준다.
     */
    fun deleteUser(id:Long) {
        Log.e(TAG, "${UserTable.ID} = ${id}")
        val deleteData = "DELETE FROM ${UserTable.TABLE_NAME} WHERE ${UserTable.ID} = ${id};"
        writableDatabase.use {
            writableDatabase.execSQL(deleteData)
        }
    }

    /**
     * 사용자 정보를 수정해준다.
     */
    fun updateUser(id:Long, userInfo: UserInfo) {
        var info = ContentValues()
        info.put(UserTable.NAME, userInfo.name)
        info.put(UserTable.AGE, userInfo.age)
        info.put(UserTable.TELNUM, userInfo.telNum)
        info.put(UserTable.PIC_PATH, userInfo.pic_path)

        writableDatabase.use {
            writableDatabase.update(UserTable.TABLE_NAME, info, "${UserTable.ID} = ${id}", null)
        }
    }
    //////////////////////////////////////////////////////////
    // SQLiteOpenHelper 사용 시에 사용하는 반드시 Override 할 함수
    //////////////////////////////////////////////////////////
    override fun onCreate(database: SQLiteDatabase?) {
        database?.createTable(UserTable.TABLE_NAME, true,
                Pair(UserTable.ID, INTEGER+ PRIMARY_KEY),
                Pair(UserTable.NAME, TEXT),
                Pair(UserTable.AGE, TEXT),
                Pair(UserTable.TELNUM, TEXT),
                Pair(UserTable.PIC_PATH, TEXT));
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}