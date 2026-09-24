/*
 * Copyright (c) 2026 OddzMint.
 * Licensed under the MIT License - see LICENSE file in the project root.
 */
package com.oddzmint.lemon.data.mapper

import com.oddzmint.lemon.data.local.MenuItemEntity
import com.oddzmint.lemon.data.remote.dto.MenuItemDto
import com.oddzmint.lemon.domain.model.MenuItem

fun MenuItemDto.toEntity(): MenuItemEntity {
    return MenuItemEntity(
        id = id,
        title = title,
        price = price
    )
}


fun MenuItemEntity.toDomain(): MenuItem {
    return MenuItem(
        id = id,
        title = title,
        price = price
    )
}