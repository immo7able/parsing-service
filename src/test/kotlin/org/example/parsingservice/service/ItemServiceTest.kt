package org.example.parsingservice.service

import org.example.parsingservice.domain.entity.Item
import org.example.parsingservice.domain.exception.NotFoundException
import org.example.parsingservice.repository.ItemRepository
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ItemServiceTest {

    private val itemRepository: ItemRepository = Mockito.mock(ItemRepository::class.java)
    private val restTemplate: RestTemplate = Mockito.mock(RestTemplate::class.java)
    private val itemService = DefaultItemService(itemRepository, restTemplate)

    @Test
    fun `findById should return item when found`() {
        val itemId = "123"
        val item = Item(
            id = itemId,
            name = "Test Item",
            price = BigDecimal(100),
            description = "desc",
            icon = "icon"
        )
        Mockito.`when`(itemRepository.findById(itemId)).thenReturn(Optional.of(item))

        val result = itemService.findById(itemId)

        assertNotNull(result)
        assertEquals(itemId, result.id)
        assertEquals(BigDecimal(100), result.price)
    }

    @Test
    fun `findById should throw NotFoundException when item is missing`() {
        val itemId = "456"
        Mockito.`when`(itemRepository.findById(itemId)).thenReturn(Optional.empty())

        val exception = assertThrows<NotFoundException> {
            itemService.findById(itemId)
        }

        assertEquals("Item with id $itemId Not Found", exception.message)
    }

    @Test
    fun `updatePrice should update and save item`() {
        val itemId = "789"
        val oldItem = Item(
            id = itemId,
            name = "Old Item",
            price = BigDecimal(200),
            description = "desc",
            icon = "icon"
        )
        Mockito.`when`(itemRepository.findById(itemId)).thenReturn(Optional.of(oldItem))
        Mockito.`when`(itemRepository.save(any())).thenAnswer { it.arguments[0] }

        val updatedItem = itemService.updatePrice(itemId, BigDecimal(300))

        assertNotNull(updatedItem)
        assertEquals(BigDecimal(300), updatedItem.price)
        verify(itemRepository, times(1)).save(any())
    }
}