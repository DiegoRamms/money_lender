package com.appgame.prestador.presentation.contacts.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appgame.prestador.R
import com.appgame.prestador.databinding.ActivityMainBinding
import com.appgame.prestador.utils.CONTACT
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var contactDetailFragment: ContactDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootView)

        supportActionBar?.hide()

        if (savedInstanceState == null) {
            contactDetailFragment = ContactDetailFragment.newInstance()
            contactDetailFragment?.arguments = Bundle().apply {
                putParcelable(CONTACT,intent.getParcelableExtra(CONTACT))
            }
            contactDetailFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.root_view, it, ContactDetailFragment.TAG)
                    .commit()
            }
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        contactDetailFragment?.let {
            supportFragmentManager.putFragment(outState,ContactDetailFragment.TAG,it)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        supportFragmentManager.getFragment(savedInstanceState, ContactDetailFragment.TAG)?.let{
            contactDetailFragment = it as ContactDetailFragment
        }
    }

}