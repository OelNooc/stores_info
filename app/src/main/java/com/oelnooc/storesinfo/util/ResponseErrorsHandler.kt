package com.oelnooc.storesinfo.util

import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar

object ResponseErrorsHandler {

    fun handleHttpError(view: View, code: Int, message: String?) {
        val errorMessage = when (code) {
            in 400..499 -> "Client error: $code - $message"
            in 500..599 -> "Server error: $code - $message"
            else -> "Unexpected error: $code - $message"
        }

        Log.e("ResponseErrorsHandler", errorMessage)

        Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    fun handleGenericError(view: View, message: String?) {
        val errorMessage = "Network error or unexpected error: $message"
        Log.e("ResponseErrorsHandler", errorMessage)

        Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}