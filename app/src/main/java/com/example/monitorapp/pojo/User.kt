package com.example.monitorapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("userTable",)
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name:String,
    var password:String,
    var age:Int
)
