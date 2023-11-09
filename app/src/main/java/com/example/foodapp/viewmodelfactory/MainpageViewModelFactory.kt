package com.example.foodapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.viewmodel.MainPageViewModel

class MainpageViewModelFactory(private var application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainPageViewModel(application) as T
    }
}