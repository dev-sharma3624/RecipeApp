package com.example.recipeapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


@Composable
fun MainScreen(navigateToDetail: (Category) -> Unit,
               currentDisplayState : RecipeViewModel.LoadState){

    Log.d("MainScreen", "Called")

    Box (
        modifier = Modifier.fillMaxSize()
    ) {
        when {

            currentDisplayState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            currentDisplayState.error != null -> {
                Text(text = "Error occurred while loading data! \n${currentDisplayState.error}")
            }

            else -> {
                GridItems(categories = currentDisplayState.data, navigateToDetail = navigateToDetail)
            }
        }
    }
}


@Composable
fun GridItems(categories : List<Category>, navigateToDetail: (Category) -> Unit){
    LazyVerticalGrid (
        GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    )
    {
        items (categories) {
            IndividualItems(item = it, navigateToDetail = navigateToDetail)
        }
    }
}


@Composable
fun IndividualItems (item : Category, navigateToDetail: (Category) -> Unit){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .clickable { Log.d("MainScreen", "navigateToDetail called")
                navigateToDetail(item) }
    )
    {
        Image(
            painter = rememberAsyncImagePainter(model = item.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1F)
            )
        Text(
            text = item.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
}