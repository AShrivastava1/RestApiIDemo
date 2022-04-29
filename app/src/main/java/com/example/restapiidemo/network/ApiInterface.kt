package com.example.restapiidemo.network

import com.example.restapiidemo.home.data.PostModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("products")
    fun fetchAllPosts(): Call<List<PostModel>>
}