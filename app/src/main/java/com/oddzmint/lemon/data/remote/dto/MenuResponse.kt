package com.oddzmint.lemon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MenuResponse(
    val menu: List<MenuItem>
)

@Serializable
data class MenuItem(
    val id: Int,
    val title: String,
    val price: String
)