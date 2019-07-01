package com.dynamiteam.lyahlaundrydriver.fragments.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dynamiteam.lyahlaundrydriver.R
import com.dynamiteam.lyahlaundrydriver.activities.MainCallback
import com.dynamiteam.lyahlaundrydriver.base.BaseFragment
import com.dynamiteam.lyahlaundrydriver.data.model.request.LoginRequest
import com.dynamiteam.lyahlaundrydriver.data.model.response.LoginResponse
import com.dynamiteam.lyahlaundrydriver.tools.PrefManager
import com.dynamiteam.lyahlaundrydriver.tools.bindInterfaceOrThrow
import com.dynamiteam.lyahlaundrydriver.tools.extentions.setClickListeners
import com.dynamiteam.lyahlaundrydriver.tools.extentions.text
import kotlinx.android.synthetic.main.fr_login.*

class LoginFragment : BaseFragment<LoginVM>(), View.OnClickListener {


    override val layoutId = R.layout.fr_login
    override val viewModelClass = LoginVM::class.java


    private var passwordValidationObserver = Observer<Boolean> {

        if (it) {
            callback?.hideProgress();
            loginPasswordContainer.error = getString(R.string.password_cannot_be_empty)
        }
    }

    private var emailValidationObserver = Observer<Boolean> {
        if (it) {
            callback?.hideProgress();
            loginEmailContainer.error = getString(R.string.email_cannot_be_empty)
        }
    }

    private var loginResponseObserver = Observer<LoginResponse> {
        callback?.hideProgress()
        callback?.showSnack(it.id.toString())
        PrefManager.saveLoginSession(loginEmailField.text(),it)
        PrefManager.isFirstOpen = false
        callback?.showHomeFragment()
    }

    override fun observeLiveData() {
        viewModel.run {
            passwordValidationLD.observe(this@LoginFragment, passwordValidationObserver)
            emailValidationLD.observe(this@LoginFragment, emailValidationObserver)
            loginResponseLD.observe(this@LoginFragment, loginResponseObserver)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setClickListeners(loginButton)
    }

    override fun onClick(v: View?) {
        when (v) {
            loginButton -> {
                callback?.showProgress()
                var loginRequest = LoginRequest(loginEmailField.text(), loginPasswordField.text())
                viewModel.doLogin(loginRequest)
            }
        }
    }

    var callback: MainCallback? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = bindInterfaceOrThrow<MainCallback>(parentFragment, context)
    }
}