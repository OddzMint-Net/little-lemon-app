package com.oddzmint.lemon.data.repository

import com.oddzmint.lemon.data.local.MenuDao
import com.oddzmint.lemon.data.mapper.toDomain

import com.oddzmint.lemon.data.remote.network.MenuApi
import com.oddzmint.lemon.domain.repository.MenuRepository
import com.oddzmint.lemon.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.oddzmint.lemon.data.mapper.toEntity

class MenuRepositoryImpl @Inject constructor(
    private val menuApi: MenuApi,
    private val menuDao: MenuDao
) : MenuRepository {

    override fun observeMenu(): Flow<List<MenuItem>> {
        return menuDao.observeMenu().map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun refreshMenu() {
        menuDao.replaceAll(menuApi.getMenu().menu.map { it.toEntity() })
    }
}