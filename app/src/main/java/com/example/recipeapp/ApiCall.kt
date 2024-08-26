package com.example.recipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//Create a retrofit instance
val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create()).build()

//Create an api service instance
val getResponse = retrofit.create(ApiCall::class.java)


//Define the API endpoints
interface  ApiCall{
    @GET("categories.php")
    suspend fun getData() : CategoryList
}



