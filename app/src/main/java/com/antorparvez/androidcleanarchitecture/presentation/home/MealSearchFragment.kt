package com.antorparvez.androidcleanarchitecture.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.GridLayoutManager
import com.antorparvez.androidcleanarchitecture.databinding.FragmentMealDetailsBinding
import com.antorparvez.androidcleanarchitecture.domain.model.Meal
import kotlinx.coroutines.flow.collect

class MealSearchFragment : Fragment() {
    private lateinit var binding:FragmentMealDetailsBinding
    private var searchList = arrayListOf<Meal>()
    private val viewModel:MealSearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val productImageAdapter by lazy {
        SearchAdapter(
            searchList
        ) {
            onClick(it)
        }
    }

    private fun onClick(it: Int) {
        Toast.makeText(requireContext(), "Clicked item $it", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentMealDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query.let {
                    if (query != null) {
                        viewModel.getMealSearchList(query)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }

        })


        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.searchList.collect { it ->
                if (it.isLoading){

                    Log.d("TAG", "onViewCreated: Loading....")
                 }
                if (it.error.isNotBlank()){
                    Log.d("TAG", "onViewCreated: Error !!!")
                }

                it.list?.let {data->
                    Log.d("TAG", "onViewCreated: Success")
                    searchList.addAll(data)
                }
            }
        }

        binding.searchList.apply {
            binding.searchList.layoutManager = GridLayoutManager(
                requireContext(), 2,
                GridLayoutManager.VERTICAL, false
            )
            binding.searchList.adapter = productImageAdapter
        }


    }
}