package com.example.itemsapp.ui

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itemsapp.R
import com.example.itemsapp.adapters.ItemAdapter
import com.example.itemsapp.api.RetrofitInstance
import com.example.itemsapp.repo.ItemRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var itemviewModel: ItemViewModel
    private lateinit var itemadapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        requestPermission()
        itemviewModel = ItemViewModel(application, ItemRepository(RetrofitInstance.api))
        setupRecyclerView()
        GlobalScope.launch {
            itemviewModel.itemFlow.collectLatest {
                itemadapter.submitData(it)
            }
        }

    }
    private fun hasInternetConnection() =
        ActivityCompat.checkSelfPermission(this,android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission(){
        val permissionToRequest = mutableListOf<String>()
        if(!hasInternetConnection()){
            permissionToRequest.add(android.Manifest.permission.INTERNET)
        }
        if(permissionToRequest.isNotEmpty())
        {
            ActivityCompat.requestPermissions(this,permissionToRequest.toTypedArray(),0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0 && grantResults.isNotEmpty())
        {
            for (i in grantResults.indices){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("Permission Request","${permissions[i]}granted")
                }
            }
        }
    }
    private fun setupRecyclerView(){
        itemadapter = ItemAdapter()
        rvItems.apply {
            adapter = itemadapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

}