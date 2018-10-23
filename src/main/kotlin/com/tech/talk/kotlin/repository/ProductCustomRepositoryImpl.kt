package com.tech.talk.kotlin.repository

import com.mongodb.client.result.DeleteResult
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class ProductCustomRepositoryImpl(val template: ReactiveMongoTemplate) : ProductCustomRepository {

    override fun deleteBySku(sku: String): Mono<DeleteResult> {
        val query = Query(
            where("sku").`is`(sku)
        )

        return template.remove(query, "product")
    }
}
