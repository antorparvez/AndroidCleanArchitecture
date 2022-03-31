package com.antorparvez.androidcleanarchitecture.domain.repository

import com.antorparvez.androidcleanarchitecture.data.dto_model.MealDTO
import com.antorparvez.androidcleanarchitecture.data.dto_model.MealsDTO

interface ApiRepository {
    suspend fun getMealList(s : String): MealsDTO

    suspend fun getMealDetails(s: String):MealsDTO
}