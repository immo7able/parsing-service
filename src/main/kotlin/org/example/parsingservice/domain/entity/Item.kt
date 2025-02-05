package org.example.parsingservice.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection = "items")
data class Item(
    @Id
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    var price: BigDecimal,
)