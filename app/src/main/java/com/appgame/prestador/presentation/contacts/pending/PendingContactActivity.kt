package com.appgame.prestador.presentation.contacts.pending

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appgame.prestador.R
import com.appgame.prestador.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PendingContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var pendingContactFragment: PendingContactFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.rootView)

        if (savedInstanceState == null){
            pendingContactFragment = PendingContactFragment.newInstance()
            pendingContactFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.root_view,it,PendingContactFragment.TAG)
                    .commit()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        pendingContactFragment?.let {
            supportFragmentManager.putFragment(outState,PendingContactFragment.TAG,it)
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        supportFragmentManager.getFragment(savedInstanceState,PendingContactFragment.TAG)?.let {
            pendingContactFragment =  it as PendingContactFragment
        }


    }

}