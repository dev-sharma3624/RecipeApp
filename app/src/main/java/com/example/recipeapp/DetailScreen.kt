package com.example.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailScreen(category: Category){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = category.strCategory,
            textAlign = TextAlign.Justify,
            style = TextStyle(fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp
            )
            )

        Image(painter = rememberAsyncImagePainter(model = category.strCategoryThumb)
                ,contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .aspectRatio(1f)
        )

        Text(text = category.strCategoryDescription,
            modifier = Modifier.verticalScroll(rememberScrollState()),
            textAlign = TextAlign.Justify
            )
    }
}