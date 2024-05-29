package com.oelnooc.storesinfo.util

import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.oelnooc.storesinfo.util.snackbar.DefaultSnackbarImp
import com.oelnooc.storesinfo.util.snackbar.SnackbarProvider

object ResponseErrorsHandler {

    var snackbarProvider: SnackbarProvider = DefaultSnackbarImp()

    fun handleHttpError(view: View, code: Int, message: String?) {
        val errorMessage = when (code) {
            in 400..499 -> "Client error: $code - $message"
            in 500..599 -> "Server error: $code - $message"
            else -> "Unexpected error: $code - $message"
        }

        snackbarProvider.showSnackbar(view, errorMessage, Snackbar.LENGTH_LONG)
    }

    fun handleGenericError(view: View, message: String?) {
        val errorMessage = "Network error or unexpected error: $message"

        snackbarProvider.showSnackbar(view, errorMessage, Snackbar.LENGTH_LONG)
    }
}