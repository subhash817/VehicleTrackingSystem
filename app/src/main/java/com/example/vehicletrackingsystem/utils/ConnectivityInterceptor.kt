package com.example.vehicletrackingsystem.utils

import com.eksource.events.utils.SharedPreferencesHelper
import okhttp3.*
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ConnectivityInterceptor: Interceptor {

    private lateinit var request: Request

    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
        try {
             request =
                    chain.request().newBuilder()
                        .header(
                            "Authorization",
                            "Bearer " + SharedPreferencesHelper.instance?.getString(
                                Constants.ID_TOKEN
                            )!!
                        )

                        .build()
            val response = chain.proceed(request)

            val bodyString = response.body!!.string()

            return response.newBuilder()
                .body(ResponseBody.create(response.body?.contentType(), bodyString))
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            var msg = ""
            when (e) {
                is SocketTimeoutException -> {
                    msg = "Timeout - Please check your internet connection"
                }
                is UnknownHostException -> {
                    msg = "Unable to make a connection. Please check your internet"
                }
                is ConnectionShutdownException -> {
                    msg = "Connection shutdown. Please check your internet"
                }
                is IOException -> {
                    msg = "Server is unreachable, please try again later."
                }
                is IllegalStateException -> {
                    msg = "${e.message}"
                }
                else -> {
                    msg = "${e.message}"
                }
            }

            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(999)
                .message(msg)
                .body(ResponseBody.create(null, "{${e}}")).build()
        }
    }

   }