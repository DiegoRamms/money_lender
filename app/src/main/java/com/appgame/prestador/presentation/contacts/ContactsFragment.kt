package com.appgame.prestador.presentation.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.appgame.prestador.R
import com.appgame.prestador.databinding.FragmentContactsBinding
import com.appgame.prestador.domain.StatusResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.contact.ContactIdRequest
import com.appgame.prestador.presentation.contacts.adapter.ContactsAdapter
import com.appgame.prestador.presentation.contacts.request_pending.AddContactActivity
import com.appgame.prestador.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : Fragment() {


    private lateinit var contactsAdapter: ContactsAdapter
    private val viewModel by viewModels<ContactsViewModel>()
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding
    private val dialogLoading: LoadingDialogFragment by lazy { LoadingDialogFragment.newInstance() }
    private val dialogError: ErrorDialogFragment by lazy { ErrorDialogFragment.newInstance() }
    private var currentList: ArrayList<Contact>? = null
    private var callback: ClickDetailContact? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        listeners()
    }

    fun updateList(){
        viewModel.getContacts()
    }

    private fun initView() {

        val swipeHelper = SwipeHelper { position ->
            currentList?.let { list ->
                list[position].contactId?.let { id -> viewModel.deleteContact(ContactIdRequest(id)) }
                list.removeAt(position)
            }
            contactsAdapter.notifyItemRemoved(position)
        }

        val itemTouchHelper = ItemTouchHelper(swipeHelper)
        contactsAdapter = ContactsAdapter()
        binding?.recyclerContacts?.let {
            it.adapter = contactsAdapter
            it.layoutManager = LinearLayoutManager(requireContext())
            itemTouchHelper.attachToRecyclerView(it)
        }

    }

    private fun listeners() {
        contactsAdapter.setOnItemListener {
            callback?.onClickContact(it)
        }

        binding?.fabAddContact?.setOnClickListener {
            startForResultAddContact.launch(
                Intent(
                    requireContext(),
                    AddContactActivity::class.java
                )
            )
            activity?.overridePendingTransition(
                R.anim.slide_enter_from_bottom,
                R.anim.slide_exit_to_top
            )
        }

        binding?.swipe?.setOnRefreshListener {
            binding?.swipe?.isRefreshing = false
            viewModel.getContacts()
        }

    }


    private fun initObserver() {
        viewModel.contacts.observe(viewLifecycleOwner, { result ->
            when (result.status) {
                StatusResult.LOADING -> {
                    initDialog()
                }
                StatusResult.OK -> {
                    result.data?.let {
                        currentList = it as ArrayList<Contact>
                        contactsAdapter.submitList(currentList)
                    }

                    if (result.data.isNullOrEmpty()) {
                        initEmptyErrorView()
                    } else clearEmptyView()

                }

                StatusResult.BAD -> {
                    initDialogError(result.message)
                }
            }
        })

        viewModel.contactDeleted.observe(viewLifecycleOwner, {
            when (it.status) {
                StatusResult.LOADING -> initDialog()
                StatusResult.OK -> {
                    viewModel.getContacts()
                    binding?.root?.showShortSnackBar(it.message)
                }
                StatusResult.BAD -> {
                    initDialogError(it.message)
                    viewModel.getContacts()
                }
            }
        })

        viewModel.dialogLoading.observe(viewLifecycleOwner, {
            if (!it) dialogLoading.dismiss()
        })

    }

    private fun initEmptyErrorView() {
        binding?.incEmptyView?.root?.visibility = View.VISIBLE
        binding?.incEmptyView?.lottieEmptyError?.animate()
        binding?.incEmptyView?.tvEmptyError?.apply {
            alpha = 0f
            animate()
                .alpha(1f).duration = 2000
        }
    }

    private fun clearEmptyView() {
        binding?.incEmptyView?.root?.visibility = View.GONE
    }

    private fun initDialogError(message: String) {
        dialogError.showDialog(parentFragmentManager, message)
    }

    private fun initDialog() {
        viewModel.setDialogLoadingTrue()
        dialogLoading.showDialog(parentFragmentManager)
    }

    companion object {
        val TAG: String = ContactsFragment::class.java.simpleName
        fun newInstance() = ContactsFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = context as ClickDetailContact
        }catch (e: ClassCastException){}

    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogLoading.onDetach()
    }

    private val startForResultAddContact =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        }


    interface ClickDetailContact{
        fun onClickContact(contact: Contact)
    }
}