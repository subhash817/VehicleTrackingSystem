package com.example.vehicletrackingsystem.utils

import com.example.vehicletrackingsystem.model.loginModel.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("user/login")
    suspend fun userLogin(
        @Field("mobile") mobile: String,
        @Field("password") password: String,
        @Field("imeino") imei_no: String,
        @Field("fcm_token") fcm_token: String, ): Response<LoginResponse>


}