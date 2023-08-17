package com.example.monitorapp.databases.repo

import com.example.monitorapp.databases.DataBase
import com.example.monitorapp.databases.daos.UserDao
import com.example.monitorapp.pojo.User

class UserRepo(val userDao: UserDao):DataBase {
    override fun <T> insert(Object: T) {
        userDao.addUser(Object as User)
    }

    override fun <T> delete(Object: T) {
        userDao.deleteUser(Object as User)
    }


    override fun deleteAll() {
        userDao.deleteAll()
    }

    override fun <T : Any> getAll(): List<T> = userDao.getAllUsers() as List<T>

    override fun <T:Any> getOne(value: T): List<T> = userDao.getAllUsersByName(value as String) as List<T>
}