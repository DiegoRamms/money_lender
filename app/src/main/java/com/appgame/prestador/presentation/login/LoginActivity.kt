package com.appgame.prestador.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.appgame.prestador.R
import com.appgame.prestador.databinding.ActivityMainBinding
import com.appgame.prestador.presentation.menu.MenuActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity(), LoginFragment.ClickLogin {

    private lateinit var binding: ActivityMainBinding
    private var loginFragment: LoginFragment? = null
    private var isLogout = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(applicationContext,R.color.salmon)
        supportActionBar?.hide()

        getIntentValues()


        if (savedInstanceState == null){
            loginFragment = LoginFragment.getInstance()
            loginFragment?.arguments = Bundle().apply { putBoolean("IS_LOG_OUT",isLogout) }
            supportFragmentManager.beginTransaction()
                .replace(R.id.root_view,loginFragment!!, LoginFragment.TAG)
                .commit()
        }
    }

    private fun getIntentValues() {
        isLogout =intent.getBooleanExtra("IS_LOG_OUT",false)
    }

    override fun onClickLogin() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

}