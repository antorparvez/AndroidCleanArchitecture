package com.antorparvez.androidcleanarchitecture.data.remote

import com.antorparvez.androidcleanarchitecture.data.dto_model.MealDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface AppRemoteAPI {

    @GET("/search.php")
    suspend fun getMealList(@Query("s") s:String) : MealDTO

    @GET("/lookup.pnp")
    suspend fun getMealDetails(@Query("i") i:String):MealDTO

}