package com.example.myapplicationkotlin.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplicationkotlin.dao.CoinPriceInfoDao
import com.example.myapplicationkotlin.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    companion object {
        private var db: AppDatabase? = null
        private val LOCK = Any()

    fun getInstance(context: Context): AppDatabase {
        synchronized(LOCK) {
            db?.let { return it }
            val instance = Room.databaseBuilder(context, AppDatabase::class.java, "main.db")
                .build()
            db = instance
            return instance
        }
    }
    }

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao
}