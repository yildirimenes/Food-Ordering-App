package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodapp.entity.Foods
import com.example.foodapp.ui.theme.FoodAppTheme
import com.example.foodapp.view.DetailPage
import com.example.foodapp.view.MainPage
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageController()
                }
            }
        }
    }
}

@Composable
fun PageController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainpage"){
        composable("mainpage"){
            MainPage(navController = navController)
        }
        composable("detail_page/{food}", arguments = listOf(
            navArgument("food"){type = NavType.StringType}
        )){
            val json = it.arguments?.getString("food")
            val food = Gson().fromJson(json, Foods::class.java)
            DetailPage(foods = food)
        }
    }
}

