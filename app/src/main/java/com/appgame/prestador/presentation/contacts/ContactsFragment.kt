package com.appgame.prestador.presentation.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.appgame.prestador.R
import com.appgame.prestador.databinding.FragmentContactsBinding
import com.appgame.prestador.domain.StatusResult
import com.appgame.prestador.presentation.contacts.adapter.ContactsAdapter
import com.appgame.prestador.presentation.contacts.request_pending.AddContactActivity
import com.appgame.prestador.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ContactsFragment : Fragment() {

    @Inject
    lateinit var contactsAdapter: ContactsAdapter
    private val viewModel by viewModels<ContactsViewModel>()
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding
    private var dialogLoading: LoadingDialogFragment? = null
    private var dialogError: ErrorDialogFragment? = null

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

    private fun initView() {
        binding?.recyclerContacts?.adapter = contactsAdapter
        binding?.recyclerContacts?.layoutManager = LinearLayoutManager(context)
    }

    private fun listeners() {
        contactsAdapter.setOnItemListener {
            requireContext().toastShort(it.email)
        }

        binding?.fabAddContact?.setOnClickListener {
            startForResultAddContact.launch(Intent(requireContext(), AddContactActivity::class.java))
            activity?.overridePendingTransition(R.anim.slide_enter_from_bottom, R.anim.slide_exit_to_top)
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
                        contactsAdapter.setContacts(it)
                    }

                    if (result.data.isNullOrEmpty()){
                        initEmptyErrorView()
                    }

                    dialogLoading?.dismiss()
                }

                StatusResult.BAD -> {
                    dialogLoading?.dismiss()
                    //context?.simpleDialog(result.message,"Error al intentar ob",{dialog,_ -> dialog.dismiss()})
                    initDialogError(result.message)
                }
            }
        })


    }

    private fun initEmptyErrorView(isError: Boolean = false){
        binding?.incEmptyView?.root?.visibility = View.VISIBLE
        binding?.incEmptyView?.lottieEmptyError?.animate()
        binding?.incEmptyView?.tvEmptyError?.apply {
            alpha = 0f
            animate()
                .alpha(1f).duration = 2000
        }
    }

    private fun initDialogError(message: String) {
        dialogError = ErrorDialogFragment.newInstance()
        dialogError?.setMessage(message)
        dialogError?.show(parentFragmentManager, ErrorDialogFragment.TAG)
        dialogError?.clickRetry {
            viewModel.refresh()
            Toast.makeText(requireContext(), "Click", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initDialog() {
        dialogLoading = LoadingDialogFragment.newInstance()
        dialogLoading?.show(parentFragmentManager, LoadingDialogFragment.TAG)
    }

    companion object {
        val TAG: String = ContactsFragment::class.java.simpleName
        fun getInstance() = ContactsFragment()
    }


    private val startForResultAddContact = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

    }

}