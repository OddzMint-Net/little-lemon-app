/*
 * Copyright (c) 2026 OddzMint.
 * Licensed under the MIT License - see LICENSE file in the project root.
 */
package com.oddzmint.lemon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MenuResponseDto(
    val menu: List<MenuItemDto>
)

@Serializable
data class MenuItemDto(
    val id: Int,
    val title: String,
    val price: String
)