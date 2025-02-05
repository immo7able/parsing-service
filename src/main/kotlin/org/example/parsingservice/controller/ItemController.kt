package org.example.parsingservice.controller

import jakarta.validation.constraints.Positive
import org.example.parsingservice.domain.dto.ItemDto
import org.example.parsingservice.domain.dto.toDto
import org.example.parsingservice.service.ItemService
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/items")
class ItemController(private val itemService: ItemService) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ItemDto? = itemService.findById(id)?.toDto()

    @PostMapping("/{id}")
    fun updatePrice(
        @PathVariable id: String,
        @Positive(message = "Price should be greater than 0") @RequestParam price: BigDecimal
    ): ItemDto? = itemService.updatePrice(id, price)?.toDto()
}