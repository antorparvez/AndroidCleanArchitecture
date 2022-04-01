package com.antorparvez.androidcleanarchitecture.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antorparvez.androidcleanarchitecture.common.Resource
import com.antorparvez.androidcleanarchitecture.domain.model.Meal
import com.antorparvez.androidcleanarchitecture.domain.usecases.GetSearchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(
    private val searchListUseCase: GetSearchListUseCase
) : ViewModel() {

    private val _searchList = MutableStateFlow(MealSearchState())
    val searchList : StateFlow<MealSearchState> = _searchList

    fun getMealSearchList(searchData:String){
        searchListUseCase(searchData).onEach {
            when(it){
                is Resource.Loading->{
                    _searchList.value = MealSearchState(isLoading = false)
                }

                is Resource.Error->{
                    _searchList.value = MealSearchState(error = it.message.toString())

                }
                is Resource.Success->{
                    _searchList.value= MealSearchState(list = it.data)

                }
            }
        }.launchIn(viewModelScope)
    }
}


data class MealSearchState(
    val list : List<Meal>?=null,
    val error:String ="",
    val isLoading:Boolean=false

)