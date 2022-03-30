package com.example.itemsapp.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.itemsapp.api.ItemApi
import com.example.itemsapp.model.ApiResponseItem
import retrofit2.HttpException
import java.io.IOException

class ItemPagingSource (
    private val api: ItemApi
        ) : PagingSource<Int, ApiResponseItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiResponseItem> {

        val position = params.key ?: 0
        Log.e("API","api called after $position")


        return try {
            val data = itemInfo("9a917180-f9b7-465f-b1d6-dd1b4cb5a9e6","","ALL", position,false)
            val list = ArrayList<itemInfo>()
            list.add(data)

            val response = api.pushPost(list)
            Log.e("API","api call successful")
            val result = response.body()

            LoadResult.Page(
                data = result!!,
                prevKey = if (position == 0) null else position -1,
                nextKey = if (result.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ApiResponseItem>): Int? {
        Log.e("API","get refresh call")
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}