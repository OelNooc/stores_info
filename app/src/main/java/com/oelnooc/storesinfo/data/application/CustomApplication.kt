package com.oelnooc.storesinfo.data.application

import android.app.Application

class CustomApplication: Application() {
    companion object {
        lateinit var instance: CustomApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}