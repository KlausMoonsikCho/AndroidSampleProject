package com.klaus.registerusers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.klaus.registerusers.common.CompanionObj
import com.klaus.registerusers.common.CompanionObj.Companion.REQEST_ANKO_DSL
import com.klaus.registerusers.common.CompanionObj.Companion.REQUEST_ADD_USER
import com.klaus.registerusers.common.CompanionObj.Companion.REQUEST_UPDATE_USER
import com.klaus.registerusers.common.CompanionObj.Companion.REQUEST_UPDATE_USER_ID
import com.klaus.registerusers.common.CompanionObj.Companion.REQUEST_UPDATE_USER_NAME
import com.klaus.registerusers.common.CompanionObj.Companion.REQUEST_UPDATE_USER_PIC_PATH
import com.klaus.registerusers.common.CompanionObj.Companion.REQUEST_UPDATE_USER_TELNUM
import com.klaus.registerusers.database.DatabaseHandler_Anko

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mAdapter:UserListAdapter ?= null
    private var mDatabaseHandler:DatabaseHandler_Anko = DatabaseHandler_Anko(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 툴바를 설정 -> action_main.xml 에 리소스 ID 가 있음
        val toolBar : Toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)

        val newOne = mDatabaseHandler.getUserAllWithCursor()
        if(newOne?.count != 0) {
            mAdapter = UserListAdapter(this, newOne)
            val listView = findViewById<ListView>(R.id.user_list) as ListView
            mAdapter?.setOnItemClickListener(this)
            listView.adapter = mAdapter
        }
    }

    /**
     * 사용자 정보를 입력 후에 추가 버튼을 누르면, 가져와서 ListView 에 추가해준다
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_ADD_USER -> {
                // 사용자 정보를 추가해서 ListView 에서 나올 수 있게 해준다.
                val newOne = mDatabaseHandler.getUserAllWithCursor()
                if(mAdapter==null) {
                    mAdapter = UserListAdapter(applicationContext, newOne)
                    val listView = findViewById<ListView>(R.id.user_list) as ListView
                    listView.adapter = mAdapter
                }
                mAdapter?.changeCursor(newOne)
                mAdapter?.notifyDataSetInvalidated()
            }
            // MainActivity 까지 종료하도록 함
            /*REQEST_ANKO_DSL-> {
                finish()
            }*/
        }
    }

    /**
     * 삭제 버튼을 눌렀을 때 정보를 지워준다.
     */
    fun onClickDelete(view: View) {
        Toast.makeText(this, "onClickDelete ${view.tag}", Toast.LENGTH_SHORT).show()
        mDatabaseHandler.deleteUser(view.tag as Long)
        val newOne = mDatabaseHandler.getUserAllWithCursor()
        mAdapter?.changeCursor(newOne)
    }

    /**
     * ListView 를 클릭했을 때 동작한다.
     */
    override fun onClick(view: View?) {
        var holder = view?.tag as ViewHolder
        var id = holder.del.tag as Long
        Toast.makeText(this, "onClick id = ${id}", Toast.LENGTH_SHORT).show()
        val intent: Intent = Intent(this, SaveUserActivity::class.java)
        intent.putExtra(REQUEST_UPDATE_USER, true)
        intent.putExtra(REQUEST_UPDATE_USER_ID, id)
        intent.putExtra(REQUEST_UPDATE_USER_NAME, holder.name.text)
        intent.putExtra(REQUEST_UPDATE_USER_TELNUM, holder.tel.text)
        startActivityForResult(intent, REQUEST_ADD_USER)

    }

    /**
     * 어플이 종료가 되면 종료 시켜주어야하는 것들 종료한다.
     */
    override fun onDestroy() {
        super.onDestroy()
        mAdapter?.cursor?.close()
        mDatabaseHandler?.close()
    }

    /////////////////////////
    // 메뉴 관련 CallBack
    // 상단에 구성되는 UI
    /////////////////////////
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.add_user -> {
                // 메뉴에서 add 를 선택했을 떄 처리
                Toast.makeText(this, "press AddUser", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, SaveUserActivity::class.java)
                startActivityForResult(intent, REQUEST_ADD_USER)
            }
            R.id.anko -> {
                Toast.makeText(this, "press Anko", Toast.LENGTH_SHORT).show()
                val layout: Intent = Intent(this, AnkDSLActivity::class.java)
                startActivity(layout)
                // MainActivity 까지 종료하도록 함
                // startActivityForResult(layout, REQEST_ANKO_DSL)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
