package com.appgame.prestador.presentation.menu

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.appgame.prestador.R
import com.appgame.prestador.databinding.FragmentMenuBinding
import com.appgame.prestador.presentation.contacts.ContactsFragment
import com.appgame.prestador.presentation.login.LoginActivity
import com.appgame.prestador.main.MainFragment
import com.appgame.prestador.presentation.contacts.pending.PendingContactFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding
    private var mainFragment: MainFragment? = null
    private var contactsFragment: ContactsFragment? = null
    private var pendingContactsFragment: PendingContactFragment? = null
    private val viewModel by viewModels<MenuViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(savedInstanceState)
        initObserver()


    }


    private fun initObserver() {
        viewModel.isLogout.observe(viewLifecycleOwner, {
            if (it) {
                Intent(context, LoginActivity::class.java).apply {
                    putExtra("IS_LOG_OUT", true)
                    startActivity(this)
                }
                activity?.finish()
            }
        })

    }

    private fun initView(savedInstanceState: Bundle?) {
        binding?.bottomNavigationMenu?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    setPageOne()
                }
                R.id.page_2 -> {
                    setPageTwo()
                }
            }
            true
        }
        binding?.bottomNavigationMenu?.selectedItemId = R.id.page_1

        binding?.toolbar?.setOnMenuItemClickListener { item ->
            when (item.itemId) {

                R.id.logout -> {
                    viewModel.logOut()
                    true
                }

                R.id.alert -> {
                    initPendingContactsFragment(savedInstanceState)
                    true
                }


                else -> {
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
                    super.onOptionsItemSelected(item)
                }
            }
        }
    }


    private fun initPendingContactsFragment(savedInstanceState: Bundle?) {
            pendingContactsFragment = PendingContactFragment.newInstance()
            pendingContactsFragment?.let {
                parentFragmentManager.beginTransaction()
                    .add(R.id.root_view, it, PendingContactFragment.TAG)
                    .addToBackStack(PendingContactFragment.TAG)
                    .commit()
            }
    }

    private fun setPageOne() {
        if (mainFragment == null) {
            mainFragment = MainFragment.getInstance()
        }
        mainFragment?.let {
            parentFragmentManager.beginTransaction()
                .replace(R.id.content_view, it, MainFragment.TAG)
                .commit()
        }
    }

    private fun setPageTwo() {
        if (contactsFragment == null) {
            contactsFragment = ContactsFragment.getInstance()
        }
        contactsFragment?.let {
            parentFragmentManager.beginTransaction()
                .replace(R.id.content_view, it, ContactsFragment.TAG).commit()
        }
    }

    /*private var badge: BadgeDrawable? = null

    @SuppressLint("UnsafeOptInUsageError")
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        requireContext().toastShort("0")
        badge?.let {
            binding?.toolbar?.let {
                toolbar ->
                BadgeUtils.detachBadgeDrawable(it,toolbar,R.id.alert)
            }
        }

            badge = BadgeDrawable.create(requireContext()).also {
                requireContext().toastShort("1")
                binding?.toolbar?.let { toolbar ->
                    BadgeUtils.attachBadgeDrawable(it,toolbar, R.id.alert)
                    requireContext().toastShort("2")
                }
            }



        badge?.let {
            it.backgroundColor =  ContextCompat.getColor(requireContext(),R.color.teal_200)
            it.badgeGravity = BadgeDrawable.TOP_END

        }

    }*/


    companion object {
        val TAG: String = MenuFragment::class.java.simpleName

        fun getInstance() = MenuFragment()
    }

}