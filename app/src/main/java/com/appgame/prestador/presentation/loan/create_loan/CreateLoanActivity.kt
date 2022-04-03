package com.appgame.prestador.presentation.loan.create_loan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.appgame.prestador.R
import com.appgame.prestador.databinding.ActivityMainBinding
import com.appgame.prestador.model.contact.Contact
import com.appgame.prestador.utils.CONTACT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateLoanActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var createLoanFragment: CreateLoanFragment? = null
    private var contact : Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootView)

        contact = intent.getParcelableExtra(CONTACT)

        supportActionBar?.hide()

        window.statusBarColor = ContextCompat.getColor(applicationContext,R.color.salmon)

        if (savedInstanceState == null){
            createLoanFragment = CreateLoanFragment.newInstance()
            val bundle = Bundle().apply { putParcelable(CONTACT,contact) }
            createLoanFragment?.arguments = bundle
            createLoanFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.root_view,it,CreateLoanFragment.TAG)
                    .commit()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        createLoanFragment?.let {
            supportFragmentManager.putFragment(outState,CreateLoanFragment.TAG,it)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        supportFragmentManager.getFragment(savedInstanceState,CreateLoanFragment.TAG)?.let {
            createLoanFragment = it as CreateLoanFragment
        }
    }

}