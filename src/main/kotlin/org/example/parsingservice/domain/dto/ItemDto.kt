package org.example.parsingservice.domain.dto

import org.example.parsingservice.domain.entity.Item
import java.math.BigDecimal

data class ItemDto(
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    val price: BigDecimal,
)

fun Item.toDto() = ItemDto(
    id = id,
    name = name,
    description = description,
    icon = icon,
    price = price,
)