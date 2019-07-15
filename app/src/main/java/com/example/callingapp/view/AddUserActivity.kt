package com.example.callingapp.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.callingapp.util.Constants
import com.example.callingapp.util.Util
import com.example.callingapp.database.AppDatabase
import com.example.callingapp.viewModel.AddUserViewModel
import kotlinx.android.synthetic.main.add_user_activity.*


class AddUserActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var addUserViewModel: AddUserViewModel
    private var userImageBitmap: Bitmap? = null
    private val CAMERAREQUEST = 1111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.callingapp.R.layout.add_user_activity)

        db = AppDatabase.getDatabase(applicationContext)

        addUserViewModel = ViewModelProviders.of(this).get(AddUserViewModel::class.java)

        cameraIcon.setOnClickListener {
            if (Util.getInstance().checkPermission(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    Constants.PERMISSION_CODE_CAMERA)) {
                startCameraActivity()
            }
        }

        saveUserButton.setOnClickListener {
            val userName = userName.text.trim().toString()
            val userNumber = userMobileNumber.text.trim().toString()

            addUserViewModel.addUser(this, userName, userNumber, userImageBitmap)
            finish()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constants.PERMISSION_CODE_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCameraActivity()
                } else {
                    Util.getInstance().showToast(this, "Please grant Camera permission from Settings to take pictures.")
                }
            }
            Constants.PERMISSION_CODE_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCameraActivity()
                } else {
                    Util.getInstance()
                        .showToast(this, "Please grant Storage permission from Settings to take pictures.")
                }
            }
        }
    }

    private fun startCameraActivity() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERAREQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERAREQUEST) {
            val imageData = data?.extras?.get("data")
            if (imageData != null) {
                userImageBitmap = imageData as Bitmap
                userImage.setImageBitmap(userImageBitmap)
            }
        }
    }
}