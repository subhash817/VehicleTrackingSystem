package com.example.vehicletrackingsystem.repository

import com.example.vehicletrackingsystem.utils.RetrofitInstance

class UserRepository {

    suspend fun loginUser(mobile: String, password: String, imeino: String, fcm_token: String)=
        RetrofitInstance.api.userLogin(mobile,password,imeino,fcm_token)
}