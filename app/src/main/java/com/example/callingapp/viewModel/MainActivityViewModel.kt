package com.example.callingapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.callingapp.model.UserDetails
import com.example.callingapp.repository.UserDetailsRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private var userDetailsRepository: UserDetailsRepository = UserDetailsRepository(application)
    private var userList: LiveData<List<UserDetails>>? = userDetailsRepository.getAllUsersLiveData()

    fun getUserList(): LiveData<List<UserDetails>>? {
//            = runBlocking {
//        async(IO) {
//            val userArrayList = db.userDao().getAll()
//            userList = MutableLiveData()
//            userList.postValue(userArrayList)
//            userList = db.userDao().getAll()
//            if(userList!=null){
//                userList.value
//            }
//        }.await()
//        userList
        return userList
    }


    fun getUserLists(): List<UserDetails>? {
        return userDetailsRepository.getAllUsers()
    }
}