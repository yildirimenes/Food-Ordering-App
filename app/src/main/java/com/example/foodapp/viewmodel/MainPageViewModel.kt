package com.example.foodapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.entity.Foods
import com.example.foodapp.repo.FoodsDaoRepository

class MainPageViewModel(application: Application) : AndroidViewModel(application) {
    var foodsList = MutableLiveData<List<Foods>>()
    var frepo = FoodsDaoRepository(application)

    init {
        loadFoods()
        foodsList = frepo.getFoods()
    }

    fun loadFoods(){
        frepo.getAllFoods()
    }
}