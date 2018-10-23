package com.tech.talk.kotlin.controller

import com.tech.talk.kotlin.domain.Product
import com.tech.talk.kotlin.service.ProductService
import org.springframework.http.HttpStatus.ACCEPTED
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class ProductController(val productService: ProductService) {

    @GetMapping("/{sku}")
    fun findBySku(@PathVariable("sku") sku: String): Mono<Product> {
        return this.productService.findBySku(sku)
    }

    @GetMapping
    fun findAll(): Flux<Product> {
        return this.productService.findAll()
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody product: Product): Mono<Product> {
        return this.productService.save(product)
    }

    @DeleteMapping("/{sku}")
    fun delete(@PathVariable("sku") sku: String): Mono<ResponseEntity<Void>> {
        return this.productService.deleteBySku(sku)
            .map {
                when (it.deletedCount) {
                    1L -> ResponseEntity.noContent().build<Void>()
                    else -> ResponseEntity.notFound().build<Void>()
                }
            }
    }

    @PutMapping("/{sku}")
    @ResponseStatus(ACCEPTED)
    fun save(@PathVariable("sku") sku: String, @RequestBody product: Product): Mono<Product> {

        val productCopy = product.copy(sku = sku)

        return this.productService.save(productCopy)
    }

    @GetMapping("/{sku}/slug-name")
    fun getSlugName(@PathVariable("sku") sku: String): Mono<String> {
        return this.productService.findBySku(sku).map { it.slugName }
    }
}
