package org.example.parsingservice.service

import org.example.parsingservice.domain.entity.Item
import org.example.parsingservice.domain.exception.NotFoundException
import org.example.parsingservice.repository.ItemRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

@Service
class DefaultItemService(
    private val itemRepository: ItemRepository,
    private val restTemplate: RestTemplate,
) : ItemService {

    private val logger: Logger = LoggerFactory.getLogger(DefaultItemService::class.java)

    @Scheduled(fixedRateString = "\${item.updateRate}")
    fun fetch() {
        val response = restTemplate.getForObject("https://aitec.one/testapp/items.json", Array<Item>::class.java)
        response?.let { itemRepository.saveAll(it.toList()) }
    }

    override fun findById(id: String): Item? = itemRepository.findById(id).orElseThrow {
        logger.error("Item not found with id: {$id}")
        NotFoundException("Item with id $id Not Found")
    }

    override fun updatePrice(id: String, newPrice: BigDecimal): Item? {
        val item = findById(id)
        item?.price = newPrice
        return item?.let { itemRepository.save(it) }
    }
}