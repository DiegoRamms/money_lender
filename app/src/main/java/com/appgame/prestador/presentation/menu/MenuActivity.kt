package com.appgame.prestador.presentation.menu

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.appgame.prestador.R
import com.appgame.prestador.databinding.ActivityMenuBinding
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.main.MainFragment
import com.appgame.prestador.presentation.contacts.ContactsFragment
import com.appgame.prestador.presentation.contacts.detail.ContactDetailActivity
import com.appgame.prestador.presentation.contacts.pending.PendingContactActivity
import com.appgame.prestador.presentation.login.LoginActivity
import com.appgame.prestador.presentation.menu.adapter.ViewPagerAdapter
import com.appgame.prestador.utils.CONTACT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity(), ContactsFragment.ClickDetailContact {

    private lateinit var binding: ActivityMenuBinding
    private var mainFragment: MainFragment? = null
    private var contactsFragment: ContactsFragment? = null
    private val viewModel by viewModels<MenuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initView()
        initObserver()


    }

    private fun initObserver() {
        viewModel.isLogout.observe(this, {
            if (it) {
                Intent(applicationContext, LoginActivity::class.java).apply {
                    putExtra("IS_LOG_OUT", true)
                    startActivity(this)
                }
                finish()
            }
        })
    }

    private fun initView() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        mainFragment = MainFragment.newInstance()
        contactsFragment = ContactsFragment.newInstance()
        mainFragment?.let {
            viewPagerAdapter.addFragment(it)
        }
        contactsFragment?.let {
            viewPagerAdapter.addFragment(it)
        }

        binding.viewPager.adapter = viewPagerAdapter

        binding.bottomNavigationMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    binding.viewPager.setCurrentItem(0, false)
                }
                R.id.page_2 -> {
                    binding.viewPager.setCurrentItem(1, false)
                }
            }
            true
        }


        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when (position) {
                    0 -> binding.bottomNavigationMenu.menu.findItem(R.id.page_1)?.isChecked =
                        true
                    1 -> binding.bottomNavigationMenu.menu.findItem(R.id.page_2)?.isChecked =
                        true
                }
            }
        })


        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {

                R.id.logout -> {
                    viewModel.logOut()
                    true
                }

                R.id.alert -> {
                    initPendingContactsFragment()
                    true
                }


                else -> {
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
                    super.onOptionsItemSelected(item)
                }
            }
        }

        binding.bottomNavigationMenu.selectedItemId = R.id.page_1
    }


    override fun onClickContact(contact: Contact) {
        Intent(applicationContext, ContactDetailActivity::class.java).apply {
            this.putExtra(CONTACT, contact)
            startActivity(this)
        }
    }

    private fun initPendingContactsFragment() {
        val intent = Intent(applicationContext, PendingContactActivity::class.java)
        startForResultPendingContact.launch(intent)
    }

    private val startForResultPendingContact =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                contactsFragment?.updateList()
            }
        }
}