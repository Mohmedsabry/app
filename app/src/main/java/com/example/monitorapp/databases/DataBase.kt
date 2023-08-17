package com.example.monitorapp.databases

interface DataBase {
    fun<T> insert(Object:T)
    fun<T> delete(Object:T)
    fun deleteAll()
    fun<T:Any>getAll():List<T>
    fun<T:Any>getOne(value: T):List<T>
}