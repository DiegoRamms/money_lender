package com.appgame.prestador.presentation.contacts.request_pending

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import com.appgame.prestador.R
import com.appgame.prestador.databinding.ActivityMainBinding
import com.appgame.prestador.utils.toastLong
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddContactActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var addContactFragment: AddContactFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootView)

        window?.statusBarColor = ContextCompat.getColor(applicationContext,R.color.purple_200)
        supportActionBar?.hide()

        if (savedInstanceState == null){
            addContactFragment = AddContactFragment.newInstance()
            addContactFragment?.let {
                supportFragmentManager.commit {
                    replace(R.id.root_view,it, AddContactFragment.TAG)
                }
            }
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        addContactFragment?.let {
            supportFragmentManager.putFragment(outState, AddContactFragment.TAG,it)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (addContactFragment == null){
            addContactFragment = supportFragmentManager.getFragment(savedInstanceState,
                AddContactFragment.TAG
            ) as AddContactFragment
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        overridePendingTransition(R.anim.slide_enter_from_top,R.anim.slide_exit_to_bottom)
    }



}