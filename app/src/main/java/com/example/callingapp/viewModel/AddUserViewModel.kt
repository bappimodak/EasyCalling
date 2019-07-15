package com.example.callingapp.viewModel

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import com.example.callingapp.util.Util
import com.example.callingapp.database.AppDatabase
import com.example.callingapp.model.UserDetails
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddUserViewModel(app: Application) : AndroidViewModel(app) {
    private lateinit var db: AppDatabase

    fun addUser(context: Context, userName: String, userMobileNumber: String, userImage: Bitmap?) {
        db = AppDatabase.getDatabase(context)

        val userImageInString = Util.getInstance().BitMapToString(userImage)
        val userDetails = UserDetails(userMobileNumber, userName, userImageInString)

        GlobalScope.launch {
            db.userDao().insertUser(userDetails)
        }
    }
}