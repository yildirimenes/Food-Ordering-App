package com.example.foodapp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodapp.R
import com.example.foodapp.entity.Foods

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(foods: Foods) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = foods.food_name)},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.main_color),
                    titleContentColor = colorResource(id = R.color.white)
                )
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val activity = (LocalContext.current as Activity)
                Image(bitmap = ImageBitmap.imageResource(id = activity.resources.getIdentifier(
                    foods.food_image_name,"drawable",activity.packageName
                )), contentDescription = "", modifier = Modifier.size(250.dp))
                Text(text = "${foods.food_price} ₺", color = Color.Blue, fontSize = 50.sp)
                Button(onClick = {
                    Log.e("Yemek","${foods.food_name} sipariş verildi")
                },
                    modifier = Modifier.size(250.dp,50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.main_color),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Sipariş Ver")
                }
            }
        }
    )
}