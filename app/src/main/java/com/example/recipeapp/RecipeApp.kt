package com.example.recipeapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RecipeApp(){

    Log.d("RecipeApp", "Called")

    val recipeViewModel : RecipeViewModel = viewModel()
    val displayState by recipeViewModel.categoryState
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainScreen"){
        composable("MainScreen"){

            Log.d("RecipeApp", "Calling MainScreen")

            MainScreen(navigateToDetail = {
                        navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                        navController.navigate("DetailScreen")
                    }
                ,currentDisplayState = displayState )
        }
        composable("DetailScreen"){
            val category = navController
                .previousBackStackEntry?.
                savedStateHandle?.
                get<Category>("cat") ?: Category("", "", "", "")
            DetailScreen(category = category)
        }
    }
}