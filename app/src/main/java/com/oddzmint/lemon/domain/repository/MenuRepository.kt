/*
 * Copyright (c) 2026 OddzMint.
 * Licensed under the MIT License - see LICENSE file in the project root.
 */
package com.oddzmint.lemon.domain.repository


import com.oddzmint.lemon.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    fun observeMenu(): Flow<List<MenuItem>>
    suspend fun refreshMenu()
}