package com.example.itemsapp.data

import com.google.gson.annotations.SerializedName

data class itemInfo (
    @SerializedName("storeId")val storeId : String?,
    @SerializedName("searchString")val searchString : String?,
    @SerializedName("categoryId")val categoryId : String?,
    @SerializedName("offset")val offset : Int? = 0,
    @SerializedName("isLowCountItems")val isLowCountItems : Boolean
        )