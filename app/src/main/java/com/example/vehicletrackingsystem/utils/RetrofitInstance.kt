package com.example.vehicletrackingsystem.utils

import com.example.vehicletrackingsystem.utils.Constant.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {

        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(getIntercepter())
                .build()
            Retrofit.Builder()
                .baseUrl("http://sipshrms.in/vts/index.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        private fun getIntercepter(): Interceptor {
            return ConnectivityInterceptor()
        }

        val api by lazy {
            retrofit.create(ApiInterface::class.java)
        }
    }
}