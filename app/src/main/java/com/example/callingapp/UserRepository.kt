package com.example.callingapp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.v4.content.ContextCompat.getDrawable
import com.example.callingapp.model.UserDetails

class UserRepository {
    private lateinit var userList: ArrayList<UserDetails>

    companion object {
        var userRepository: UserRepository? = null

        fun getInstances(): UserRepository? {
            if(userRepository == null){
                userRepository= UserRepository()
            }
            return userRepository
        }
    }

    fun getUserList(context: Context): MutableLiveData<List<UserDetails>> {
        setUserList(context)
        val data: MutableLiveData<List<UserDetails>> = MutableLiveData()
        data.value = userList
        return data
    }

    private fun setUserList(context: Context){
        userList = ArrayList()
        userList.add(UserDetails("bappi", "8297736038", getDrawable(context, R.drawable.avatar)))
        userList.add(UserDetails("bappi", "8297736038", getDrawable(context, R.drawable.avatar)))
        userList.add(UserDetails("bappi", "8297736038", getDrawable(context, R.drawable.avatar)))
        userList.add(UserDetails("bappi", "8297736038", getDrawable(context, R.drawable.avatar)))
        userList.add(UserDetails("bappi", "8297736038", getDrawable(context, R.drawable.avatar)))
        userList.add(UserDetails("bappi", "8297736038", getDrawable(context, R.drawable.avatar)))
        userList.add(UserDetails("bappi", "8297736038", getDrawable(context, R.drawable.avatar)))
        userList.add(UserDetails("bappi", "8297736038", getDrawable(context, R.drawable.avatar)))
        userList.add(UserDetails("bappi", "8297736038", getDrawable(context, R.drawable.avatar)))
        userList.add(UserDetails("bappi", "8297736038", getDrawable(context, R.drawable.avatar)))
    }
}