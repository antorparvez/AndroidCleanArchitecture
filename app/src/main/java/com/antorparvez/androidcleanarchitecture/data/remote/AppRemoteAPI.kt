package com.antorparvez.androidcleanarchitecture.data.remote

import com.antorparvez.androidcleanarchitecture.data.dto_model.MealDTO
import com.antorparvez.androidcleanarchitecture.data.dto_model.MealsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface AppRemoteAPI {

    @GET("search.php")
    suspend fun getMealList(@Query("s") s:String) : MealsDTO

    @GET("/lookup.pnp")
    suspend fun getMealDetails(@Query("i") i:String):MealsDTO

}