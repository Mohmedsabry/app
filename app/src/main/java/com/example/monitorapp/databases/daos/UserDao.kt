package com.example.monitorapp.databases.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.monitorapp.pojo.User
@Dao
interface UserDao {
    @Insert
    fun addUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("Delete from userTable")
    fun deleteAll()

    @Query("select * from userTable")
    fun getAllUsers(): List<User>

    @Query("select * from userTable where name = :name")
    fun getAllUsersByName(name: String): List<User>

}