package com.dynamiteam.lyahlaundry.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dynamiteam.lyahlaundry.R
import com.dynamiteam.lyahlaundry.base.BaseActivity
import com.dynamiteam.lyahlaundry.fragments.login.LoginFragment
import com.dynamiteam.lyahlaundry.tools.LOGIN_FRAGMENT
import com.dynamiteam.lyahlaundry.tools.extentions.hide
import com.dynamiteam.lyahlaundry.tools.extentions.setClickListeners
import com.dynamiteam.lyahlaundry.tools.extentions.show
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainActivityVM>(), MainCallback, View.OnClickListener {


    override val layoutId = R.layout.activity_main
    override val containerId = R.id.mainContainer
    override val viewModelClass = MainActivityVM::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLoginFragment()
        setClickListeners(progressContainer)
    }

    override fun observeLiveData() {

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