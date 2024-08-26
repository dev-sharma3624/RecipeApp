package com.example.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel(){

    data class LoadState(
        val isLoading : Boolean = true,
        val data : List<Category> = emptyList(),
        val error : String? = null
    )

    private val _categoryState = mutableStateOf(LoadState())
    val categoryState : State<LoadState> = _categoryState

    init{
        fetchData()
    }


    private fun fetchData(){
        viewModelScope.launch {
            try {
                //make an API request
                val response = getResponse.getData()
                _categoryState.value = _categoryState.value.copy(
                    data = response.categories,
                    isLoading = false,
                    error = null
                )
            }catch (e : Exception){
                _categoryState.value = _categoryState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

}