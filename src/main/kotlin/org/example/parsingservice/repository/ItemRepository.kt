package org.example.parsingservice.repository

import org.example.parsingservice.domain.entity.Item
import org.springframework.data.mongodb.repository.MongoRepository

interface ItemRepository : MongoRepository<Item, String>