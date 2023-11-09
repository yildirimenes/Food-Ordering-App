package com.example.foodapp.repo
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.entity.Foods
import com.example.foodapp.room.DB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FoodsDaoRepository(application: Application) {
    private var foodLists = MutableLiveData<List<Foods>>()
    private var vt:DB

    init {
        vt = DB.databaseAccess(application)!!
        foodLists = MutableLiveData()
    }

    fun getFoods():MutableLiveData<List<Foods>> {
        return foodLists
    }

    fun getAllFoods(){
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            foodLists.value = vt.foodDao().allFoods()
        }
    }
}