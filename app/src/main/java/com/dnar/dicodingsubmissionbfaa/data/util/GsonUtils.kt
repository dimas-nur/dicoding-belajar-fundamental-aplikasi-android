package com.dnar.dicodingsubmissionbfaa.data.util

import android.content.Context
import java.io.IOException

// Util for read data from file json; Keyword : Util
fun getDataFromJsonAsset(context: Context, fileName: String): String? {
    val jsonString: String

    // Try read json file and convert into string
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}