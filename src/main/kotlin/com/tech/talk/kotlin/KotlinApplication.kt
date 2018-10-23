package com.tech.talk.kotlin

import com.tech.talk.kotlin.domain.Product
import com.tech.talk.kotlin.repository.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Flux

@SpringBootApplication
class KotlinApplication(val productRepository: ProductRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        Flux.range(0, 10)
            .map { Product(sku = "123-$it", name = "Name $it", marketAble = true) }
            .transform { productRepository.saveAll(it) }
            .subscribe()
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinApplication>(*args)
}
