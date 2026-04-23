package com.oddzmint.lemon.data.repository

import com.oddzmint.lemon.data.local.MenuDao
import com.oddzmint.lemon.data.remote.dto.MenuItem
import com.oddzmint.lemon.data.remote.network.MenuApi
import com.oddzmint.lemon.ui.mapper.toDomain
import com.oddzmint.lemon.ui.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MenuRepository(
    private val menuApi: MenuApi,
    private val menuDao: MenuDao
) {

    fun observeMenu(): Flow<List<MenuItem>> {
        return menuDao.observeMenu().map { entities -> entities.map { it.toDomain() } }
    }

    suspend fun refreshMenu() {
        val remoteItems = menuApi.getMenu().menu
        val entities = remoteItems.map { it.toEntity() }
        menuDao.clearAll()
        menuDao.insertAll(entities)
    }
//
//    suspend fun getMenu(): List<MenuItem> {
//        return menuApi.getMenu().menu
//    }
}