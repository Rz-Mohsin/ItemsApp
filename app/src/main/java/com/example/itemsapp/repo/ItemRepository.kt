package com.example.itemsapp.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.itemsapp.data.ItemPagingSource
import com.example.itemsapp.api.ItemApi
import com.example.itemsapp.data.itemInfo
import com.example.itemsapp.model.ApiResponseItem

class ItemRepository(
 val api: ItemApi
) {


    suspend fun getAllItems(list: ArrayList<itemInfo>): LiveData<PagingData<ApiResponseItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ItemPagingSource(api) }
        ).liveData
    }
}