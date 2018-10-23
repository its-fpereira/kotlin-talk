package com.tech.talk.kotlin.repository

import com.tech.talk.kotlin.domain.Product
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface ProductRepository : ReactiveMongoRepository<Product, String>, ProductCustomRepository {

    fun findBySku(sku: String): Mono<Product>

}
