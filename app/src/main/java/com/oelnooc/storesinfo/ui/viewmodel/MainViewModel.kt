package com.oelnooc.storesinfo.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oelnooc.storesinfo.R
import com.oelnooc.storesinfo.data.api.FrogmiClient
import com.oelnooc.storesinfo.data.api.FrogmiService
import com.oelnooc.storesinfo.data.application.CustomApplication
import com.oelnooc.storesinfo.data.model.FrogmiResponse
import com.oelnooc.storesinfo.data.model.Store
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val callService: FrogmiService = FrogmiClient.getInstance()

    private val _stores = MutableLiveData<MutableList<Store>>(mutableListOf())
    val stores: LiveData<MutableList<Store>> get() = _stores

    private val _nextPageUrl = MutableLiveData<String?>()
    val nextPageUrl: LiveData<String?> get() = _nextPageUrl

    private val token = "Bearer ${CustomApplication.instance.getString(R.string.api_token)}"
    private val companyUUID = CustomApplication.instance.getString(R.string.company_uuid)

    private var isLoading = false
    init {
        fetchFirstPage()
    }

    private fun fetchFirstPage() {
        if (isLoading) return
        isLoading = true
        viewModelScope.launch {
            val call = callService.getFirstPage(token, companyUUID)
            call.enqueue(object : Callback<FrogmiResponse> {
                override fun onResponse(call: Call<FrogmiResponse>, response: Response<FrogmiResponse>) {
                    isLoading = false
                    if (response.isSuccessful) {
                        val callResponse = response.body()
                        _stores.value?.clear() // Limpiar la lista antes de añadir nuevos elementos
                        callResponse?.data?.let { storeList ->
                            Log.d("MainViewModel", "Number of stores fetched: ${storeList.size}")
                            _stores.value?.addAll(storeList.take(10)) // Asegurarse de solo agregar 10
                            _stores.value = _stores.value // Trigger observers
                        }
                        _nextPageUrl.value = callResponse?.links?.next
                    }
                }

                override fun onFailure(call: Call<FrogmiResponse>, t: Throwable) {
                    isLoading = false
                    // Handle error
                }
            })
        }
    }

    fun fetchNextPage() {
        if (isLoading || _nextPageUrl.value == null) return
        isLoading = true
        viewModelScope.launch {
            _nextPageUrl.value?.let { nextPageUrl ->
                val call = callService.getNextPage(token, companyUUID, nextPageUrl)
                call.enqueue(object : Callback<FrogmiResponse> {
                    override fun onResponse(call: Call<FrogmiResponse>, response: Response<FrogmiResponse>) {
                        isLoading = false
                        if (response.isSuccessful) {
                            val callResponse = response.body()
                            callResponse?.data?.let { storeList ->
                                Log.d("MainViewModel", "Number of additional stores fetched: ${storeList.size}")
                                _stores.value?.addAll(storeList.take(10))
                                _stores.value = _stores.value // Trigger observers
                            }
                            _nextPageUrl.value = callResponse?.links?.next
                        }
                    }

                    override fun onFailure(call: Call<FrogmiResponse>, t: Throwable) {
                        isLoading = false
                        // Handle error
                    }
                })
            }
        }
    }
}