package com.example.monitorapp.screens.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monitorapp.databases.daos.Api
import com.example.monitorapp.pojo.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeVM : ViewModel() {
    private lateinit var list: Call<List<Post>>
    private val mutableStateFlow: MutableStateFlow<List<Post>> = MutableStateFlow(emptyList<Post>())
    val flow: Flow<List<Post>> = mutableStateFlow

    init {
        getPostsCallBack()
    }


    private fun getPostsCallBack() {
        viewModelScope.launch(Dispatchers.IO) {
            val apiInterface = apiInterface()
            list = apiInterface.getPosts()
            list.enqueue(object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    viewModelScope.launch(Dispatchers.IO) {
                        mutableStateFlow.value =
                            response.body()?.toMutableList() ?: emptyList<Post>()
                        Log.d("flow", "flow ${mutableStateFlow.value.size}")
                        flow.collect(mutableStateFlow)
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    private fun apiInterface(): Api {
        val retrofit =
            Retrofit
                .Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(Api::class.java)
    }


}