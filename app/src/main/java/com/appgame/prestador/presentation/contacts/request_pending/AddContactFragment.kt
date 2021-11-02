package com.appgame.prestador.presentation.contacts.request_pending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.appgame.prestador.R
import com.appgame.prestador.databinding.FragmentAddContactBinding
import com.appgame.prestador.domain.StatusResult
import com.appgame.prestador.domain.contact.AddContactRequest
import com.appgame.prestador.domain.contact.DeleteContactRequestRequest
import com.appgame.prestador.domain.user.SearchUserRequest
import com.appgame.prestador.presentation.contacts.adapter.ContactsRequestPendingAdapter
import com.appgame.prestador.utils.LoadingDialogFragment
import com.appgame.prestador.utils.clickWithDelay
import com.appgame.prestador.utils.toastLong
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddContactFragment private constructor() : Fragment() {

    private var _binding: FragmentAddContactBinding? = null
    private val binding get() = _binding
    private val viewModel: AddContactViewModel by viewModels()
    private val dialogLoading: LoadingDialogFragment by lazy { LoadingDialogFragment.newInstance() }
    private var userCode = ""
    private var userCodeFound = ""
    private lateinit var contactsRequestPendingAdapter: ContactsRequestPendingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddContactBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
        initObservers()
    }

    private fun initView() {
        dialogLoading.isCancelable = false
        contactsRequestPendingAdapter = ContactsRequestPendingAdapter()
        binding?.rvPendingContacts?.apply {
            adapter = contactsRequestPendingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.getPendingContacts()
        val textCodeForTest = "AC2E-71A7-A7AB"
        binding?.edtCode?.setText(textCodeForTest)
    }

    private fun initListeners() {
        binding?.fabSearch?.clickWithDelay {
            userCode = binding?.edtCode?.text.toString()
            viewModel.validateUserCode(userCode)
        }

        binding?.imgAdd?.clickWithDelay {
            viewModel.addContact(AddContactRequest(userCodeFound))
        }


        contactsRequestPendingAdapter.setOnItemClickListener { contact ->
            contact.idContact?.let { viewModel.deleteContact(DeleteContactRequestRequest(it)) }
        }
    }

    private fun initObservers() {

        viewModel.pendingContacts.observe(viewLifecycleOwner, {
            when (it.status) {
                StatusResult.LOADING -> initDialogLoading()
                StatusResult.OK -> contactsRequestPendingAdapter.submitList(it.data)
                StatusResult.BAD -> requireContext().toastLong(it.message)
            }
        })

        viewModel.validCode.observe(viewLifecycleOwner, { isValid ->
            if (isValid) viewModel.searchUser(SearchUserRequest(userCode))
            else {
                binding?.edtCode?.error = requireContext().getString(R.string.error_code)
                binding?.cardUserFound?.animate()?.alpha(0f)?.duration = 1000
            }
        })

        viewModel.userFound.observe(viewLifecycleOwner, {
            when (it.status) {
                StatusResult.LOADING -> {
                    initDialogLoading()
                }
                StatusResult.OK -> {
                    it.data?.let { user ->
                        userCodeFound = user.code
                        binding?.cardUserFound?.x = -1000F
                        binding?.cardUserFound?.alpha = 1f
                        binding?.cardUserFound?.visibility = View.VISIBLE
                        binding?.tvName?.text = user.name
                        binding?.tvCode?.text = user.code
                        binding?.cardUserFound?.animate()?.translationX(0f)?.duration = 800
                    }
                }
                StatusResult.BAD -> {
                    requireContext().toastLong(it.message)
                    binding?.cardUserFound?.animate()?.alpha(0f)?.duration = 1000
                }
            }
        })

        viewModel.dismissDialog.observe(viewLifecycleOwner, {
            if (it) dialogLoading.dismiss()
        })


        viewModel.contactAdded.observe(viewLifecycleOwner, {
            when (it.status) {
                StatusResult.LOADING -> {
                    initDialogLoading()
                }
                StatusResult.OK -> {
                    binding?.cardUserFound?.animate()?.alpha(0f)?.duration = 1000
                    requireContext().toastLong(it.message)
                }
                StatusResult.BAD -> {
                    requireContext().toastLong(it.message)
                }
            }
        })

        viewModel.contactDeleted.observe(viewLifecycleOwner, {
            when (it.status) {
                StatusResult.LOADING -> initDialogLoading()
                StatusResult.OK -> requireContext().toastLong(it.message)
                StatusResult.BAD -> requireContext().toastLong(it.message)
            }
        })
    }

    private fun initDialogLoading() {
        viewModel.setDismissFalse()
        if (!dialogLoading.isAdded) {
            dialogLoading.show(parentFragmentManager, LoadingDialogFragment.TAG)
        }

    }

    companion object {
        val TAG: String = AddContactFragment::class.java.simpleName
        fun newInstance() = AddContactFragment()
    }


}