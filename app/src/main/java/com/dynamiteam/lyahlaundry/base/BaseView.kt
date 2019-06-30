package com.dynamiteam.lyahlaundry.base

import androidx.lifecycle.ViewModelProvider

interface BaseView {

    fun createViewModelFactory(): ViewModelProvider.NewInstanceFactory?

    fun isNeedProgress(): Boolean

    fun showSnack(message:String)
}