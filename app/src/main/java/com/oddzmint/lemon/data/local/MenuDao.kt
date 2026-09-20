package com.oddzmint.lemon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu_items")
    fun observeMenu(): Flow<List<MenuItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<MenuItemEntity>)

    @Query("DELETE FROM menu_items")
    suspend fun clearAll()

    @Transaction
    suspend fun replaceAll(items: List<MenuItemEntity>) {
        clearAll()
        insertAll(items)
    }
}