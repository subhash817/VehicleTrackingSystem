package com.example.vehicletrackingsystem.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vehicletrackingsystem.repository.UserRepository
import com.example.vehicletrackingsystem.viewmodels.LoginViewModel

class LoginViewModelFactory(val app: Application, val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(app, userRepository) as T
    }
}