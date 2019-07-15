package com.example.callingapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.callingapp.database.AppDatabase
import com.example.callingapp.model.UserDetails
import com.example.callingapp.database.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class UserDetailsRepository(application: Application) {
    private var database: AppDatabase = AppDatabase.getDatabase(application)

    private var userDao: UserDao? = null

//    private var allUserDetailsLiveData: LiveData<List<UserDetails>>?
//    private var allUserDetails: List<UserDetails>?

    init {
        userDao = database.userDao()
//        allUserDetailsLiveData = userDao?.getAll()
//        allUserDetails = userDao?.getAlls()
    }

    fun insert(userDetails: UserDetails) {
    }

    fun deleteAllNotes() {

    }

    fun getAllUsersLiveData(): LiveData<List<UserDetails>>? {
        return null//allUserDetailsLiveData
    }

    fun getAllUsers(): List<UserDetails>? = runBlocking {
            var userArrayList: List<UserDetails>? = null
            withContext(Dispatchers.IO) {
                userArrayList = database.userDao().getAlls()
            }
            userArrayList
        }
    }