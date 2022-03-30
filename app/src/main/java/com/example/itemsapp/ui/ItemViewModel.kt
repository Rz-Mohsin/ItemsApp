package com.example.itemsapp.ui

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.*
import com.example.itemsapp.data.ItemPagingSource
import com.example.itemsapp.api.RetrofitInstance
import com.example.itemsapp.repo.ItemRepository


class ItemViewModel(
    app : Application,
    val itemRepository: ItemRepository
) : AndroidViewModel(app) {


   val itemFlow = Pager(
           config = PagingConfig(
               pageSize = 50,
               maxSize = 150,
               enablePlaceholders = false
           ),
           pagingSourceFactory = { ItemPagingSource(RetrofitInstance.api) }
       ).flow.cachedIn(viewModelScope)

}