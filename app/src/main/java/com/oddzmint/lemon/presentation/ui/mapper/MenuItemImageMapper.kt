/*
 * Copyright (c) 2026 OddzMint.
 * Licensed under the MIT License - see LICENSE file in the project root.
 */
package com.oddzmint.lemon.presentation.ui.mapper

import com.oddzmint.lemon.R
import com.oddzmint.lemon.domain.model.MenuItem
import com.oddzmint.lemon.presentation.ui.model.MenuItemUi

fun MenuItem.toUi(): MenuItemUi {
    return MenuItemUi(
        id = id,
        title = title,
        price = price,
        description = "Freshly prepared and served with Little Lemon style.",
        imageRes = getMenuItemImage(title)
    )
}

fun getMenuItemImage(title: String): Int {
    return when (title) {
        "Spinach Artichoke Dip" -> R.drawable.spinach_artichoke_dip
        "Hummus" -> R.drawable.hummus
        "Fried Calamari Rings" -> R.drawable.fried_calamari_rings
        "Fried Mushroom" -> R.drawable.fried_mushroom
        "Greek" -> R.drawable.greek
        "Caesar" -> R.drawable.caesar
        "Mediterranean Tuna Salad" -> R.drawable.mediterranean_tuna_salad
        "Grilled Chicken Salad" -> R.drawable.grilled_chicken_salad
        "Water" -> R.drawable.water
        "Coke" -> R.drawable.coke
        "Beer" -> R.drawable.beer
        "Iced Tea" -> R.drawable.ice_tea
        else -> R.drawable.logo
    }
}