package com.tech.talk.kotlin.domain

import com.tech.talk.kotlin.extension.toSlug
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.index.Indexed

@TypeAlias("product")
data class Product(
    @Id val id: String? = null,
    @Indexed(unique = true) val sku: String,
    val name: String,
    var slugName: String? = null,
    var marketAble: Boolean = false) {

    constructor(sku: String, name: String, marketAble: Boolean) : this(sku = sku, name = name) {
        this.marketAble = marketAble
        this.slugName = name.toSlug()
    }
}
