package com.example.vehicletrackingsystem.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vehicletrackingsystem.commonUtils.NetworkConnection
import com.example.vehicletrackingsystem.model.loginModel.LoginResponse
import com.example.vehicletrackingsystem.repository.UserRepository
import com.example.vehicletrackingsystem.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class LoginViewModel(app: Application, val repository: UserRepository) : AndroidViewModel(app) {
    val mutableLoginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    var loginResponse: LoginResponse? = null
    val networkConnection = NetworkConnection(app)


    fun getLogin(mobile: String, password: String, imeino: String, fcm_token: String) =
        viewModelScope.launch {
            getLoginUser(mobile, password, imeino, fcm_token)
        }

    private suspend fun getLoginUser(
        mobile: String,
        password: String,
        imeino: String,
        fcm_token: String
    ) {
        mutableLoginResponse.postValue(Resource.Loading())
        try {

            val response = repository.loginUser(mobile, password, imeino, fcm_token)
            mutableLoginResponse.postValue(handleLoginResponse(response))

        } catch (t: Throwable) {

            when (t) {
                is IOException -> mutableLoginResponse.postValue(Resource.Error("Network Failure"))
                else -> mutableLoginResponse.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleLoginResponse(response: Response<LoginResponse>): Resource<LoginResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                loginResponse = resultResponse

                return Resource.Success(loginResponse ?: resultResponse)
            }


        }
        return Resource.Error(response.message())
    }
    }