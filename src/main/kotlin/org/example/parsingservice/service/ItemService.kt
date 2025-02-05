package org.example.parsingservice.service

import org.example.parsingservice.domain.entity.Item
import java.math.BigDecimal

interface ItemService {

    fun findById(id: String): Item?

    fun updatePrice(id: String, newPrice: BigDecimal): Item?
}