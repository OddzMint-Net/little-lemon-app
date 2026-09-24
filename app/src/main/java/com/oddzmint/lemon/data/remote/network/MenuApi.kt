/*
 * Copyright (c) 2026 OddzMint.
 * Licensed under the MIT License - see LICENSE file in the project root.
 */
package com.oddzmint.lemon.data.remote.network

import com.oddzmint.lemon.data.remote.dto.MenuResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class MenuApi @Inject constructor(private val client: HttpClient) {
    suspend fun getMenu(): MenuResponseDto {
        return client.get(
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json"
        ).body()
    }
}