package com.chullian.foodbank.data.network

import com.dynamiteam.lyahlaundrydriver.data.model.response.LoginResponse
import okhttp3.RequestBody


class ApiRepo(private val mRequest: ApiService) {

    suspend fun login(loginRequest: HashMap<String, RequestBody>): LoginResponse = mRequest.login(loginRequest)
}