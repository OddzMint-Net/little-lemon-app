package com.oddzmint.lemon.data.remote.network

import com.oddzmint.lemon.data.remote.dto.MenuResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MenuApi(private val client: HttpClient) {
    suspend fun getMenu(): MenuResponse {
        return client.get(
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json"
        ).body()
    }
}