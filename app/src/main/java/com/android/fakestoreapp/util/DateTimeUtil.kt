package com.android.fakestoreapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeUtil {
    fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }
}