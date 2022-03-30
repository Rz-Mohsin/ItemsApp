package com.example.itemsapp.api

import com.example.itemsapp.data.itemInfo
import com.example.itemsapp.model.ApiResponse
import retrofit2.Response
import retrofit2.http.*

interface ItemApi {

    @POST("api/v4/stockItem/getPaginatedItemsList")
    suspend fun pushPost(
       @Body iteminfo : ArrayList<itemInfo>
    ) : Response<ApiResponse>
}