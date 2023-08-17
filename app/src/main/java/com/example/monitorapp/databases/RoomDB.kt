package com.example.monitorapp.databases

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.monitorapp.AppContext
import com.example.monitorapp.databases.daos.UserDao
import com.example.monitorapp.pojo.User

@Database(entities = [User::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object{
        fun getInstence(): RoomDB =
            Room.databaseBuilder(AppContext.contextApp, RoomDB::class.java, "userDB").build()
    }
}