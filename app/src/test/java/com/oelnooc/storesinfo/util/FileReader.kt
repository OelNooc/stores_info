package com.oelnooc.storesinfo.util

import java.io.File
import java.io.InputStreamReader
import java.lang.StringBuilder

object FileReader {

    fun lectorJson(file:String) : String{
        val input =  File("src/main/assets/$file").inputStream() //InstrumentationRegistry.getInstrumentation().targetContext.applicationContext.assets.open(archivo)
        val builder = StringBuilder()
        val lector = InputStreamReader(input,"UTF-8")
        lector.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}