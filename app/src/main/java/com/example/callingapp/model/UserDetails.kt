package com.example.callingapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserDetails")
data class UserDetails(@PrimaryKey var number: String, var userName: String, var userImage: String?)