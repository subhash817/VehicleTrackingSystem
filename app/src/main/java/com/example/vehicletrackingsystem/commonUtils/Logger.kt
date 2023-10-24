package com.example.vehicletrackingsystem.commonUtils

import android.util.Log

object Logger {

    // Define log tags for different components of your app
    private const val TAG = "MyApp"

    fun v(message: String) {
        Log.v(TAG, message)
    }

    fun d(message: String) {
        Log.d(TAG, message)
    }

    fun i(message: String) {
        Log.i(TAG, message)
    }

    fun w(message: String) {
        Log.w(TAG, message)
    }

    fun e(message: String) {
        Log.e(TAG, message)
    }
}
