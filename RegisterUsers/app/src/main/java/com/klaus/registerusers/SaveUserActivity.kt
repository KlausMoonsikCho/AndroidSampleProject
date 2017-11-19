package com.klaus.registerusers

import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.klaus.registerusers.common.CompanionObj.Companion.REQ_PERMISSION
import com.klaus.registerusers.common.CompanionObj.Companion.PICK_IMAGE
import com.klaus.registerusers.common.CompanionObj.Companion.REQUEST_UPDATE_USER
import com.klaus.registerusers.common.CompanionObj.Companion.REQUEST_UPDATE_USER_ID
import com.klaus.registerusers.common.CompanionObj.Companion.REQUEST_UPDATE_USER_NAME
import com.klaus.registerusers.common.CompanionObj.Companion.REQUEST_UPDATE_USER_TELNUM
import com.klaus.registerusers.database.DatabaseHandler_Anko
import com.klaus.registerusers.database.UserInfo
import kotlinx.android.synthetic.main.activity_save_user.*
import org.w3c.dom.Text

/**
 * Created by chomoonsik on 2017. 11. 18..
 */
/**
 * 사용자 정보를 입력할 때 사용하는 Activity 이다.
 */
class SaveUserActivity: AppCompatActivity() {
    val mDataBaseHandler = DatabaseHandler_Anko(this)
    var mSelectedImgId:Long = 0
    var mUpdateMode: Boolean = false
    var mUpdateID: Long = -1L
    var mUpdateName: String = ""
    var mUpdateTelNum: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_user)
        mUpdateMode = intent.getBooleanExtra(REQUEST_UPDATE_USER, false)
        mUpdateID = intent.getLongExtra(REQUEST_UPDATE_USER_ID, -1L)


        if(mUpdateMode && mUpdateID!=-1L) {
            mUpdateName = intent.getStringExtra(REQUEST_UPDATE_USER_NAME)!!
            mUpdateTelNum = intent.getStringExtra(REQUEST_UPDATE_USER_TELNUM)!!
            var button: Button = findViewById<Button>(R.id.btn_add) as Button
            button.setText(getString(R.string.label_save_up_btn))
            var name: EditText = findViewById<EditText>(R.id.edit_name) as EditText
            name.setText(mUpdateName)
            var telNum: EditText = findViewById<EditText>(R.id.edit_tel) as EditText
            telNum.setText(mUpdateTelNum)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun onClickImage(view: View) {
        val check = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(check!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQ_PERMISSION)
        } else {
            val i = Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, PICK_IMAGE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var notGranted = kotlin.arrayOfNulls<String>(permissions.size)
        when(requestCode) {
            REQ_PERMISSION->{
                var index:Int = 0
                for(i in 0..permissions.size-1) {
                    if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        val rationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])
                        if(!rationale) {
                            val dialogBuild = AlertDialog.Builder(this).setTitle(getString(R.string.set_permission))
                                    .setMessage(getString(R.string.set_permission_message)).setCancelable(true)
                                    .setPositiveButton(getString(R.string.go_to_set_permission)) {
                                        dialog, whichButton -> showSetting()
                                    }
                        } else {
                            notGranted[index++] = permissions[i]
                        }

                        if(notGranted.isNotEmpty()) {
                            ActivityCompat.requestPermissions(this, notGranted, REQ_PERMISSION)
                        }
                    }
                }
            }
        }
    }

    fun showSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", packageName, null))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            PICK_IMAGE->{
                val uri = data?.getData()
                uri?:return

                mSelectedImgId = getImageID(uri)
                if(mSelectedImgId == -1L) {
                    return
                }

                val bitmap: Bitmap = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, mSelectedImgId,
                        MediaStore.Images.Thumbnails.MICRO_KIND, null)

                val sel_image: ImageView = findViewById<ImageView>(R.id.sel_img) as ImageView
                sel_image.setImageBitmap(bitmap)
            }
        }
    }

    fun getImageID(uri: Uri): Long {
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val cursor = managedQuery(uri, projection, null, null, null)
        val column_index = cursor.getColumnIndex(MediaStore.Images.Media._ID)

        if(column_index == -1) {
            return -1;
        }

        cursor.moveToFirst()
        val id = cursor.getLong(column_index)
        cursor.close()
        return id
    }

    fun onClickSaveBtn(view: View) {
        val userInfo: UserInfo = UserInfo(edit_name.text.toString(), edit_age.text.toString(),
                edit_tel.text.toString(), mSelectedImgId.toString())
        if(mUpdateMode && mUpdateID!=-1L) {
            mDataBaseHandler.updateUser(mUpdateID, userInfo)
        } else {
            mDataBaseHandler.addUser(userInfo)
        }
        mDataBaseHandler.close()
        finish()
    }
}