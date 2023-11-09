package com.example.foodapp.room

import androidx.room.Dao
import androidx.room.Query
import com.example.foodapp.entity.Foods

@Dao
interface FoodsDao {
    @Query("SELECT * FROM foods")
    suspend fun allFoods():List<Foods>
}