package com.dynamiteam.lyahlaundrydriver.activities

interface MainCallback{
    fun showProgress()
    fun hideProgress()
    fun showTopbBar()
    fun hideTopbBar()
    fun showBottombBar()
    fun hideBottombBar()
    fun showToast(message: String)
    fun showSnack(message: String)

    fun makeFullScreen()
    fun cancelFullScreen()

    fun showLoginFragment()
    fun showHomeFragment()
    fun showOtpFragment(text: String, isLogin: Boolean)
    fun showDialog()

    fun positiveButtonClicked()
    fun negetiveButtonClicked()

    fun removeTopFragment()
}