package com.oelnooc.storesinfo.data.model

data class Store(
    val attributes: Attributes,
    val id: String,
    val links: StoreLink,
    val relationships: Relationships,
    val type: String
)