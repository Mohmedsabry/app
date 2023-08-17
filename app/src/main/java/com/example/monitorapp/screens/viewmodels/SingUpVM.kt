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

class SingUpVM : ViewModel() {

    private val mutableStateFlow: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val flow: StateFlow<Boolean?> = mutableStateFlow
    fun insertDB(user: User) {
        var isFound = false
        viewModelScope.launch(Dispatchers.IO) {
            val database = RoomDB.getInstence()
            val userRepo = UserRepo(database.userDao())
            userRepo.getAll<User>().forEach { eachUser ->
                if (user.name == eachUser.name) {
                    isFound = true
                }
            }
            if (!isFound) {
                userRepo.insert(user)
                database.close()
                println("done")
                mutableStateFlow.value = true
                flow.collect(mutableStateFlow)
            } else {
                mutableStateFlow.value = false
                flow.collect(mutableStateFlow)
            }
            //database.userDao().addUser(user)
        }
    }
}