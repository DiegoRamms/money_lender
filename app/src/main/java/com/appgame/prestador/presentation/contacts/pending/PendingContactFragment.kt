package com.appgame.prestador.presentation.contacts.pending

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.appgame.prestador.R
import com.appgame.prestador.databinding.FragmentPendingContactBinding
import com.appgame.prestador.model.StatusResult
import com.appgame.prestador.model.contact.ContactIdRequest
import com.appgame.prestador.presentation.contacts.adapter.ContactsPendingAdapter
import com.appgame.prestador.utils.ErrorDialogFragment
import com.appgame.prestador.utils.LoadingDialogFragment
import com.appgame.prestador.utils.simpleDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PendingContactFragment : Fragment() {

    private var _binding: FragmentPendingContactBinding? = null
    private val binding get() = _binding
    private val viewModel: PendingContactViewModel by viewModels()
    private val dialogLoading: LoadingDialogFragment by lazy { LoadingDialogFragment.newInstance() }
    private val errorDialog: ErrorDialogFragment by lazy { ErrorDialogFragment.newInstance() }
    private var contactsPendingAdapter: ContactsPendingAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPendingContactBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
        initObservers()

    }

    private fun initView() {
        contactsPendingAdapter = ContactsPendingAdapter()
        binding?.let {
            it.rvPendingContacts.adapter = contactsPendingAdapter
            it.rvPendingContacts.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initListener() {
        contactsPendingAdapter?.addOnclickListener { contact ->
            contact.contactId?.let {
                viewModel.acceptContact(ContactIdRequest(it))
                activity?.setResult(Activity.RESULT_OK)
            }
        }
        contactsPendingAdapter?.deleteRequestClickListener { contact ->
            contact.contactId?.let {
                viewModel.deleteContact(ContactIdRequest(it))
            }
        }
    }

    private fun initObservers() {
        viewModel.contactsToAccept.observe(viewLifecycleOwner, {
            when (it.status) {
                StatusResult.LOADING -> initDialog()
                StatusResult.OK -> {
                    contactsPendingAdapter?.submitList(it.data)
                    it.data?.let { list ->
                        if (list.isEmpty()) {
                            initEmptyView()
                            binding?.tvCountRequest?.text = "0"
                        } else {
                            clearEmptyView()
                            binding?.tvCountRequest?.text = list.size.toString()
                        }
                    }
                }
                StatusResult.BAD -> initErrorDialog(it.message)
            }
        })

        viewModel.contactAccepted.observe(viewLifecycleOwner, {
            when (it.status) {
                StatusResult.LOADING -> initDialog()
                StatusResult.OK -> requireContext().simpleDialog(
                    message = it.message,
                    clickPositive = { dialog, _ -> dialog.dismiss() })
                StatusResult.BAD -> initErrorDialog(it.message)
            }
        })

        viewModel.contactDeleted.observe(viewLifecycleOwner, {
            when (it.status) {
                StatusResult.LOADING -> initDialog()
                StatusResult.OK -> requireContext().simpleDialog(message = it.message)
                StatusResult.BAD -> initErrorDialog(it.message)
            }
        })

        viewModel.dataLoading.observe(viewLifecycleOwner, {
            if (!it) dialogLoading.dismiss()
        })
    }

    private fun initEmptyView() {
        binding?.incEmptyView?.root?.visibility = View.VISIBLE
        binding?.incEmptyView?.lottieEmptyError?.animate()
        binding?.incEmptyView?.tvEmptyError?.text = getString(R.string.no_requests)
        binding?.incEmptyView?.tvEmptyError?.apply {
            alpha = 0f
            animate()
                .alpha(1f).duration = 2000
        }
    }

    private fun clearEmptyView() {
        binding?.incEmptyView?.root?.visibility = View.GONE
    }

    private fun initDialog() {
        viewModel.setLoadingTrue()
        dialogLoading.showDialog(parentFragmentManager)
    }

    private fun initErrorDialog(text: String) {
        errorDialog.showDialog(parentFragmentManager, text)
    }

    companion object {
        val TAG: String = PendingContactFragment::class.java.simpleName
        fun newInstance() = PendingContactFragment()
    }

}