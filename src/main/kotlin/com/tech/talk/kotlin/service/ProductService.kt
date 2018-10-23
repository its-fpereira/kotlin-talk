package com.tech.talk.kotlin.service

import com.mongodb.client.result.DeleteResult
import com.tech.talk.kotlin.domain.Product
import com.tech.talk.kotlin.repository.ProductRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProductService(val productRepository: ProductRepository) {

    val logger: Logger by lazy { LoggerFactory.getLogger(ProductService::class.java) }

    fun findBySku(sku: String): Mono<Product> {
        return this.productRepository.findBySku(sku)
    }

    fun findAll(): Flux<Product> {
        return this.productRepository.findAll()
    }

    fun save(product: Product): Mono<Product> {
        logger.info("saving product={}", product)
        return this.productRepository.save(product)
    }

    fun deleteBySku(sku: String): Mono<DeleteResult> {
        logger.info("deleting product by sku={}", sku)
        return this.productRepository.deleteBySku(sku)
    }
}
