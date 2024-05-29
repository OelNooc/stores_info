package com.oelnooc.storesinfo.util

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.oelnooc.storesinfo.util.snackbar.SnackbarProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ResponseErrorsHandlerTest {

    private lateinit var mockView: View
    private lateinit var mockSnackbarProvider: SnackbarProvider

    @Before
    fun setUp() {
        mockView = Mockito.mock(View::class.java)
        mockSnackbarProvider = Mockito.mock(SnackbarProvider::class.java)
        ResponseErrorsHandler.snackbarProvider = mockSnackbarProvider
    }

    @Test
    fun handleHttpError_withClientError_showsCorrectSnackbarMessage() {
        val code = 404
        val message = "Not Found"
        val expectedMessage = "Client error: $code - $message"

        // Act
        ResponseErrorsHandler.handleHttpError(mockView, code, message)

        // Assert
        Mockito.verify(mockSnackbarProvider).showSnackbar(mockView, expectedMessage, Snackbar.LENGTH_LONG)
    }

    @Test
    fun handleHttpError_withServerError_showsCorrectSnackbarMessage() {
        val code = 500
        val message = "Internal Server Error"
        val expectedMessage = "Server error: $code - $message"

        // Act
        ResponseErrorsHandler.handleHttpError(mockView, code, message)

        // Assert
        Mockito.verify(mockSnackbarProvider).showSnackbar(mockView, expectedMessage, Snackbar.LENGTH_LONG)
    }

    @Test
    fun handleHttpError_withUnexpectedError_showsCorrectSnackbarMessage() {
        val code = 600
        val message = "Unknown Error"
        val expectedMessage = "Unexpected error: $code - $message"

        // Act
        ResponseErrorsHandler.handleHttpError(mockView, code, message)

        // Assert
        Mockito.verify(mockSnackbarProvider).showSnackbar(mockView, expectedMessage, Snackbar.LENGTH_LONG)
    }

    @Test
    fun handleGenericError_showsCorrectSnackbarMessage() {
        val message = "Network issue"
        val expectedMessage = "Network error or unexpected error: $message"

        // Act
        ResponseErrorsHandler.handleGenericError(mockView, message)

        // Assert
        Mockito.verify(mockSnackbarProvider).showSnackbar(mockView, expectedMessage, Snackbar.LENGTH_LONG)
    }
}