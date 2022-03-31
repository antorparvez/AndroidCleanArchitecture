package com.antorparvez.androidcleanarchitecture.domain.usecases

import com.antorparvez.androidcleanarchitecture.common.Resource
import com.antorparvez.androidcleanarchitecture.data.dto_model.toDomainMeal
import com.antorparvez.androidcleanarchitecture.data.dto_model.toDomainMealDetails
import com.antorparvez.androidcleanarchitecture.domain.model.Meal
import com.antorparvez.androidcleanarchitecture.domain.model.MealDetails
import com.antorparvez.androidcleanarchitecture.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSearchListUseCase @Inject constructor(
    private val repository: ApiRepository
) {
    operator fun invoke(s: String): Flow<Resource<List<Meal>>> = flow {

        try {
            emit(Resource.Loading())

            val response = repository.getMealList(s)
            val list =
                if (response.meals.isNullOrEmpty()) emptyList<Meal>() else response.meals.map {
                    it.toDomainMeal()
                }
            emit(Resource.Success(data = list))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))

        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check Internet Connection!!!"))
        }

    }
}


class GetMealDetailsUseCase @Inject constructor(
    private val repository: ApiRepository
) {
    operator fun invoke(s: String): Flow<Resource<MealDetails>> = flow {

        try {
            emit(Resource.Loading())

            val response = repository.getMealDetails(s).meals[0].toDomainMealDetails()
            emit(Resource.Success(response))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))

        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check Internet Connection!!!"))
        }

    }
}