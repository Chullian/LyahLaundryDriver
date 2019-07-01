package com.dynamiteam.lyahlaundrydriver.base

import androidx.lifecycle.ViewModelProvider

interface BaseView {

    fun createViewModelFactory(): ViewModelProvider.NewInstanceFactory?

    fun isNeedProgress(): Boolean

    fun showSnack(message:String)
    fun hideProgress()
}