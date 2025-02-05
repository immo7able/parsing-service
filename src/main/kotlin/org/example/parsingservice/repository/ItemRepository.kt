package org.example.parsingservice.repository

import org.example.parsingservice.domain.entity.Item
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : MongoRepository<Item, String>