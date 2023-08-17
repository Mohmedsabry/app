package com.example.monitorapp.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monitorapp.databases.RoomDB
import com.example.monitorapp.databases.repo.UserRepo
import com.example.monitorapp.pojo.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginVM : ViewModel() {
    private var mutableStateFlow: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val flow: StateFlow<List<User>> = mutableStateFlow

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val datebase = RoomDB.getInstence()
            val repo = UserRepo(datebase.userDao())
            mutableStateFlow.value = repo.getAll<User>().toMutableList()
            datebase.close()
            flow.collect(mutableStateFlow)
        }
    }

    fun checkLogin(name: String, pass: String, list: List<User>): Boolean {
        list.forEach { user ->
            if (user.name == name && user.password == pass)
                return true
        }
        return false
    }
}