package com.dynamiteam.lyahlaundry.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dynamiteam.lyahlaundry.base.BaseViewModel
import com.dynamiteam.lyahlaundry.data.model.request.LoginRequest
import com.dynamiteam.lyahlaundry.data.model.response.LoginResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody

class LoginVM : BaseViewModel() {

    val emailValidationLD = MutableLiveData<Boolean>()
    val passwordValidationLD = MutableLiveData<Boolean>()
    val loginResponseLD = MutableLiveData<LoginResponse>()

    fun doLogin(loginRequest: LoginRequest) {
        if (!checkEmptyFeild(loginRequest)) {
            viewModelScope.launch {
                try {
                    var response = api.login(createParamsToLogin(loginRequest))
                    loginResponseLD.postValue(response)
                } catch (e: Exception) {
                    errorObserverLD.postValue(e)
                }
            }
        }
    }

    private fun checkEmptyFeild(loginRequest: LoginRequest): Boolean {
        var hasEmptyField = false
        if (loginRequest.password.isNullOrEmpty()) {
            passwordValidationLD.postValue(true);hasEmptyField = true
        } else {
            passwordValidationLD.postValue(false);hasEmptyField = false
        }
        if (loginRequest.email.isNullOrEmpty()) {
            emailValidationLD.postValue(true);hasEmptyField = true
        } else {
            emailValidationLD.postValue(false);hasEmptyField = false
        }
        return hasEmptyField
    }


    private fun createParamsToLogin(loginRequest: LoginRequest): HashMap<String, RequestBody> {
        return hashMapOf(
            "email" to RequestBody.create(MediaType.parse("multipart/form-data"), loginRequest.email),
            "password" to RequestBody.create(MediaType.parse("multipart/form-data"), loginRequest.password)
        )
    }


}