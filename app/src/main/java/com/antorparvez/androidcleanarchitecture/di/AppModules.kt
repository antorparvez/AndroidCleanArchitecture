package com.antorparvez.androidcleanarchitecture.di

import com.antorparvez.androidcleanarchitecture.common.Constants
import com.antorparvez.androidcleanarchitecture.data.remote.AppRemoteAPI
import com.antorparvez.androidcleanarchitecture.data.repository_imp.DataRepository
import com.antorparvez.androidcleanarchitecture.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideRetrofit(): AppRemoteAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(AppRemoteAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideApiRepository(api : AppRemoteAPI):ApiRepository{
        return DataRepository(api)
    }
}