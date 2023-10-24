package com.example.vehicletrackingsystem.model.loginModel

import com.example.vehicletrackingsystem.model.Data

data class LoginResponse(
    val `data`: Data,
    val msg: String,
    val token: String
)