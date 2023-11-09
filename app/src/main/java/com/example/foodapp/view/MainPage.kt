package com.example.foodapp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodapp.R
import com.example.foodapp.viewmodel.MainPageViewModel
import com.example.foodapp.viewmodelfactory.MainpageViewModelFactory
import com.google.gson.Gson

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "DiscouragedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavController) {
    val context = LocalContext.current
    val viewModel: MainPageViewModel = viewModel(
        factory = MainpageViewModelFactory(context.applicationContext as Application)
    )
    val foodList = viewModel.foodsList.observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.main_color),
                    titleContentColor = colorResource(id = R.color.white),
                ),
                title = { Text(text = "Yemekler") }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.padding(top = 60.dp)
            ){
                items(
                    count = foodList.value!!.count(),
                    itemContent = {
                        val food = foodList.value!![it]
                        Card(modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth()) {
                            Row(modifier = Modifier.clickable {
                                val foodJson = Gson().toJson(food)
                                navController.navigate("detail_page/$foodJson")
                            }) {
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth()
                                ) {
                                    val activity = (LocalContext.current as Activity)
                                    Image(bitmap = ImageBitmap.imageResource(id = activity.resources.getIdentifier(
                                        food.food_image_name,"drawable",activity.packageName
                                    )), contentDescription = "", modifier = Modifier.size(100.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            modifier = Modifier.fillMaxHeight()
                                        ) {
                                            Text(text = food.food_name, fontSize = 20.sp)
                                            Spacer(modifier = Modifier.size(30.dp))
                                            Text(text = "${food.food_price} ₺", color = Color.Blue)
                                        }
                                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24), contentDescription = "")

                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    )
}