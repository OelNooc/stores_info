package com.oelnooc.storesinfo.ui.viewmodel

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
    private val _stores = MutableLiveData<List<Store>>()
    val stores: LiveData<List<Store>> get() = _stores

    private val _nextPageUrl = MutableLiveData<String?>()
    val nextPageUrl: LiveData<String?> get() = _nextPageUrl

    private val token = "Bearer ${CustomApplication.instance.getString(R.string.api_token)}"
    private val companyUUID = CustomApplication.instance.getString(R.string.company_uuid)

    init {
        fetchFirstPage()
    }

    private fun fetchFirstPage() {
        viewModelScope.launch {
            val call = callService.getFirstPage(token, companyUUID)
            call.enqueue(object : Callback<FrogmiResponse> {
                override fun onResponse(call: Call<FrogmiResponse>, response: Response<FrogmiResponse>) {
                    if (response.isSuccessful) {
                        val callResponse = response.body()
                        _stores.value = callResponse?.data
                        _nextPageUrl.value = callResponse?.links?.next
                    }
                }

                override fun onFailure(call: Call<FrogmiResponse>, t: Throwable) {
                    // Manejar el error
                }
            })
        }
    }

    fun fetchNextPage() {
        viewModelScope.launch {
            _nextPageUrl.value?.let { nextPageUrl ->
                val call = callService.getNextPage(token, companyUUID, nextPageUrl)
                call.enqueue(object : Callback<FrogmiResponse> {
                    override fun onResponse(call: Call<FrogmiResponse>, response: Response<FrogmiResponse>) {
                        if (response.isSuccessful) {
                            val callResponse = response.body()
                            val currentStores = _stores.value.orEmpty()
                            _stores.value = currentStores + (callResponse?.data.orEmpty())
                            _nextPageUrl.value = callResponse?.links?.next
                        }
                    }

                    override fun onFailure(call: Call<FrogmiResponse>, t: Throwable) {
                        // Manejar el error
                    }
                })
            }
        }
    }
}