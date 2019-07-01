package com.dynamiteam.lyahlaundrydriver.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dynamiteam.lyahlaundrydriver.R
import com.dynamiteam.lyahlaundrydriver.base.BaseActivity
import com.dynamiteam.lyahlaundrydriver.base.BaseView
import com.dynamiteam.lyahlaundrydriver.fragments.home.HomeFragment
import com.dynamiteam.lyahlaundrydriver.fragments.login.LoginFragment
import com.dynamiteam.lyahlaundrydriver.tools.HOME_FRAGMENT
import com.dynamiteam.lyahlaundrydriver.tools.LOGIN_FRAGMENT
import com.dynamiteam.lyahlaundrydriver.tools.PrefManager
import com.dynamiteam.lyahlaundrydriver.tools.extentions.hide
import com.dynamiteam.lyahlaundrydriver.tools.extentions.setClickListeners
import com.dynamiteam.lyahlaundrydriver.tools.extentions.show
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainActivityVM>(), MainCallback, View.OnClickListener, BaseView {


    override val layoutId = R.layout.activity_main
    override val containerId = R.id.mainContainer
    override val viewModelClass = MainActivityVM::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PrefManager.isFirstOpen) showLoginFragment()
        else showHomeFragment()

        setClickListeners(progressContainer)
    }

    override fun observeLiveData() {

    }

    override fun onBackPressed() {
        when (getCurrentTag()) {
            HOME_FRAGMENT -> {
                finish()
            }
            LOGIN_FRAGMENT -> {
                finish()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    override fun showProgress() {
        progressContainer.show()
        progressBar.show()
    }

    override fun hideProgress() {
        progressContainer.hide()
        progressBar.hide()
    }

    override fun showTopbBar() {

    }

    override fun hideTopbBar() {

    }

    override fun showBottombBar() {

    }

    override fun hideBottombBar() {

    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showSnack(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun makeFullScreen() {

    }

    override fun cancelFullScreen() {

    }

    override fun showLoginFragment() {
        clearStack(0)
        replaceFragment(LoginFragment(), LOGIN_FRAGMENT)
    }

    override fun showHomeFragment() {
        clearStack(0)
        replaceFragment(HomeFragment(), HOME_FRAGMENT)
    }

    override fun showOtpFragment(text: String, isLogin: Boolean) {

    }

    override fun showDialog() {

    }

    override fun positiveButtonClicked() {

    }

    override fun negetiveButtonClicked() {

    }

    override fun removeTopFragment() {

    }

    private fun clearStack(index: Int) {
        var count = supportFragmentManager.backStackEntryCount
        while (count > index) {
            supportFragmentManager.popBackStack()
            count--
        }
    }

    private fun getCurrentTag(): String = if ((supportFragmentManager.findFragmentById(R.id.mainContainer) != null)) {
        (supportFragmentManager.findFragmentById(R.id.mainContainer))?.tag ?: ""
    } else ""

    override fun onClick(v: View?) {

    }

}


