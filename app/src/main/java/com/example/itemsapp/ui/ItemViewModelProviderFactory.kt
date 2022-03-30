package com.example.itemsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.itemsapp.repo.ItemRepository

class ItemViewModelProviderFactory(
    val app : Application,
    val itemRepository: ItemRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemViewModel(app,itemRepository) as T
    }

}