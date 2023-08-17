package com.example.monitorapp

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object AppContext {
    lateinit var contextApp:Context
    fun initContext(context: Context){
        this.contextApp = context
    }
}