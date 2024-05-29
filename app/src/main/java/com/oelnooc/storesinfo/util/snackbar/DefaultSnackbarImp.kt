package com.oelnooc.storesinfo.util.snackbar

import android.view.View
import com.google.android.material.snackbar.Snackbar

class DefaultSnackbarImp : SnackbarProvider {
    override fun showSnackbar(view: View, message: String, duration: Int) {
        Snackbar.make(view, message, duration).show()
    }
}