package com.oddzmint.lemon.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MenuItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}