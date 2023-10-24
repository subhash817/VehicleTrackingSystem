package com.example.vehicletrackingsystem.commonUtils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeUtil {
    companion object {
        fun getCurrentDateTime(): String {
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return currentDateTime.format(formatter)
        }
    }
}
