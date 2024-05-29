package com.oelnooc.storesinfo.util.snackbar

import android.view.View

interface SnackbarProvider {
    fun showSnackbar(view: View, message: String, duration: Int)
}