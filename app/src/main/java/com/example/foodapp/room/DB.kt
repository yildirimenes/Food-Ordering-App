package com.example.foodapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodapp.entity.Foods

@Database(entities = [Foods::class], version = 1)
abstract class DB : RoomDatabase() {
    abstract fun foodDao():FoodsDao

    companion object{
        private var INSTANCE: DB? = null

        fun databaseAccess(context: Context): DB?{
            if (INSTANCE == null){
                synchronized(DB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DB::class.java,
                        "food.sqlite")
                        .createFromAsset("food.sqlite")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}