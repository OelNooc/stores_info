package com.oelnooc.storesinfo.data.model

import com.google.gson.annotations.SerializedName

data class Attributes(
    val active: Boolean,
    val code: String,
    val coordinates: Coordinates,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("full_address")
    val fullAddress: String,
    val name: String
)