package com.appgame.prestador.presentation.payment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appgame.prestador.R
import com.appgame.prestador.databinding.ActivityMainBinding
import com.appgame.prestador.model.loan.Loan
import com.appgame.prestador.utils.LOAN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var loan: Loan? = null
    private var paymentsFragment: PaymentsFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootView)

        supportActionBar?.hide()

        loan = intent.getParcelableExtra(LOAN)


        if (savedInstanceState == null){
            paymentsFragment = PaymentsFragment.newInstance()
            paymentsFragment?.arguments = Bundle().apply {
                putParcelable(LOAN,loan)
            }
            paymentsFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.root_view,it,PaymentsFragment.TAG)
                    .commit()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        paymentsFragment?.let {
            supportFragmentManager.putFragment(outState,PaymentsFragment.TAG,it)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        supportFragmentManager.getFragment(savedInstanceState,PaymentsFragment.TAG)?.let {
            paymentsFragment = it as PaymentsFragment
        }
    }


}