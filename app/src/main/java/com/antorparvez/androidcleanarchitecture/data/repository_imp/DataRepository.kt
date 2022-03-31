package com.antorparvez.androidcleanarchitecture.data.repository_imp

import com.antorparvez.androidcleanarchitecture.data.dto_model.MealsDTO
import com.antorparvez.androidcleanarchitecture.data.remote.AppRemoteAPI
import com.antorparvez.androidcleanarchitecture.domain.repository.ApiRepository

class DataRepository(private val api: AppRemoteAPI) : ApiRepository{
    override suspend fun getMealList(s: String): MealsDTO {
       return api.getMealList(s)
    }

    override suspend fun getMealDetails(s: String): MealsDTO {
       return api.getMealDetails(s)
    }
}