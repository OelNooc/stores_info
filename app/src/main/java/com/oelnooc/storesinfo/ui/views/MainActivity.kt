package com.oelnooc.storesinfo.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oelnooc.storesinfo.databinding.ActivityMainBinding
import com.oelnooc.storesinfo.ui.adapter.StoreAdapter
import com.oelnooc.storesinfo.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var storeAdapter: StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        viewModel.stores.observe(this, Observer { stores ->
            if (storeAdapter.itemCount == 0) {
                storeAdapter.setStores(stores)
            } else {
                storeAdapter.addStores(stores.takeLast(10))
            }
        })

        binding.storesRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (layoutManager.findLastVisibleItemPosition() == storeAdapter.itemCount - 1) {
                    viewModel.fetchNextPage(binding.root)
                }
            }
        })

        viewModel.fetchFirstPage(binding.root)
    }

    private fun setupRecyclerView() {
        storeAdapter = StoreAdapter(mutableListOf())
        binding.storesRv.apply {
            adapter = storeAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}