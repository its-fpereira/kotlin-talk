package com.tech.talk.kotlin.repository

import com.mongodb.client.result.DeleteResult
import reactor.core.publisher.Mono

interface ProductCustomRepository {
    fun deleteBySku(sku: String): Mono<DeleteResult>
}
