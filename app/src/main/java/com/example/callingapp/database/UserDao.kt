package com.example.callingapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.callingapp.model.UserDetails

@Dao
interface UserDao {

    @Query("SELECT * FROM UserDetails")
    fun getAll(): LiveData<List<UserDetails>>

    @Query("SELECT * FROM UserDetails")
    fun getAlls(): List<UserDetails>

    @Query("SELECT * FROM UserDetails WHERE number LIKE :number")
    fun findByTitle(number: String): UserDetails

//    @Query("SELECT * FROM UserDetails WHERE id = :id")
//    fun findById(id: Int): TodoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg todo: UserDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(notes: List<UserDetails>)

    @Delete
    fun delete(todo: UserDetails)

    @Update
    fun updateTodo(vararg todos: UserDetails)
}


//in case of conflict while insert: onConflict = OnConflictStrategy.REPLACE

//Returning subsets of columns