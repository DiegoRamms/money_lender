package com.appgame.prestador.presentation.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appgame.prestador.R
import com.appgame.prestador.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var menuFragment: MenuFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootView)
        supportActionBar?.hide()

        if (savedInstanceState == null){
            menuFragment = MenuFragment.getInstance()
            menuFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.root_view,it, MenuFragment.TAG)
                    .commit()
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        menuFragment?.let {
            supportFragmentManager.putFragment(outState, MenuFragment.TAG,it)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        menuFragment = supportFragmentManager.getFragment(savedInstanceState,
            MenuFragment.TAG
        ) as MenuFragment
    }
}