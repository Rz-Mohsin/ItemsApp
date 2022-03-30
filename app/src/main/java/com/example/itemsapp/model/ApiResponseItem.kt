package com.example.itemsapp.model

import java.io.Serializable

data class ApiResponseItem(
    val availableCount: String,
    val isItemCountLow: Boolean,
    val lastUpdatedTime: String,
    val lowCountLimit: Double,
    val mrp: String,
    val name: String,
    val priceUnit: String,
    val purchasePrice: String,
    val sellingPrice: String,
    val stockItemId: String,
    val storeId: String
)