package com.oelnooc.storesinfo.data.api

import com.google.common.truth.Truth
import com.oelnooc.storesinfo.util.FileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FrogmiClientTest {

    private val server = MockWebServer()
    private val body = FileReader.lectorJson("llamada.json")
    private val token = "Bearer testToken"
    private val companyUUID = "test uuid"

    @Before
    fun setUp() {
        server.start()
        server.enqueue(MockResponse().setResponseCode(200).setBody(body))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun test_apiSuccessCantidad()
    {
        val call = FrogmiClient.getInstance(server.url("/").toString()).getFirstPage(token, companyUUID)
        val storesData = call.execute().body()
        var storesList = storesData?.data
        Truth.assertThat(storesList?.size).isEqualTo(10)
    }

    @Test
    fun test_apiFailCantidad()
    {
        val call = FrogmiClient.getInstance(server.url("/").toString()).getFirstPage(token, companyUUID)
        val storesData = call.execute().body()
        var storesList = storesData?.data
        Truth.assertThat(storesList?.size).isNotEqualTo(12)
    }
}