package com.example.vehicletrackingsystem.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vehicletrackingsystem.R
import com.example.vehicletrackingsystem.commonUtils.DateTimeUtil
import com.example.vehicletrackingsystem.commonUtils.Logger
import com.example.vehicletrackingsystem.commonUtils.NetworkConnection
import com.example.vehicletrackingsystem.databinding.ActivityLoginBinding
import com.example.vehicletrackingsystem.repository.UserRepository
import com.example.vehicletrackingsystem.utils.LoginViewModelFactory
import com.example.vehicletrackingsystem.utils.Resource
import com.example.vehicletrackingsystem.viewmodels.LoginViewModel
import com.shashank.sony.fancytoastlib.FancyToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpViews()
        val layoutInflater = findViewById<View>(R.id.networkError)
        val networkConnection = NetworkConnection(applicationContext)

        networkConnection.observe(this) { isConnected ->
            if (isConnected) {
                FancyToast.makeText(
                    this,
                    "Back Online",
                    FancyToast.LENGTH_LONG,
                    FancyToast.SUCCESS,
                    true
                ).show()
                layoutInflater.visibility = View.GONE
                binding.llLogin.visibility = View.VISIBLE

            } else {
                FancyToast.makeText(
                    this,
                    "No connection",
                    FancyToast.LENGTH_LONG,
                    FancyToast.DEFAULT,
                    true
                ).show()
                layoutInflater.visibility = View.VISIBLE
                binding.llLogin.visibility = View.GONE

            }
        }

        var isAllFieldsChecked = false
        binding.loginButton.setOnClickListener {
            viewModel.getLogin("8448843888", "123", "lkjdf8sdfssdff", "jdsflkdsjlkf1")

            viewModel.mutableLoginResponse.observe(this, { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { loginResponse ->
                            if (loginResponse.data != null) {
                                Log.d("Login_Url", loginResponse.data.toString())
                                val toast = Toast.makeText(this, loginResponse.msg.toString(), Toast.LENGTH_LONG).show()
                            } else {
                                val toast = Toast.makeText(this, loginResponse.msg.toString(), Toast.LENGTH_LONG).show()
                            }

                        }
                    }

                    is Resource.Error -> {
                        response.msg?.let { message ->
                            Log.d("Error", response.msg
                            )

                            val toast = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                        }
                    }

                    else -> {

                    }
                }

            })

//            isAllFieldsChecked = checkAllFields()
//            if (isAllFieldsChecked) {
////                val intent = Intent(this@LoginActivity, SideNavigationActivity::class.java)
////                startActivity(intent)
//            }
        }
        val dateTimeUtil = DateTimeUtil.getCurrentDateTime()
        val logger = Logger
    }


    private fun checkAllFields(): Boolean {
        val userId = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()
        if (userId.isEmpty()) {
            binding.username.error = "Please Enter User Id"
            return false
        } else if (password.isEmpty()) {
            binding.password.error = "Please Enter Password"
            return false
        }
        return true

    }

    private fun setUpViews() {
        val factory = LoginViewModelFactory(application, UserRepository())
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }


}