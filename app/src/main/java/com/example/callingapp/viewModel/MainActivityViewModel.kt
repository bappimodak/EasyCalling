package com.example.callingapp.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.callingapp.UserRepository
import com.example.callingapp.model.UserDetails

class MainActivityViewModel : ViewModel() {
    private var userList: MutableLiveData<List<UserDetails>>? = null

    fun init(context: Context) {
        userList = UserRepository.getInstances()?.getUserList(context)
    }

    fun getUserList(): LiveData<List<UserDetails>>? {
        return userList
    }
}