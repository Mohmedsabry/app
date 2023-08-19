package com.example.monitorapp.databases.daos

import com.example.monitorapp.pojo.Post
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("posts")
    fun getPosts():Call<List<Post>>
    @GET("posts/:id")
    fun getPost(id:String):Call<Post>
}