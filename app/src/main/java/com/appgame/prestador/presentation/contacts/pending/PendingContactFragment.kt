package com.appgame.prestador.presentation.contacts.pending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.appgame.prestador.databinding.FragmentPendingContactBinding
import com.appgame.prestador.domain.StatusResult
import com.appgame.prestador.presentation.contacts.adapter.ContactsPendingAdapter
import com.appgame.prestador.utils.LoadingDialogFragment
import com.appgame.prestador.utils.toastShort
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PendingContactFragment: Fragment() {

    private var _binding: FragmentPendingContactBinding? = null
    private val binding get() = _binding
    private val viewModel: PendingContactViewModel by viewModels()
    private val dialogLoading: LoadingDialogFragment by lazy { LoadingDialogFragment.newInstance() }
    private var contactsPendingAdapter: ContactsPendingAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPendingContactBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
        initObservers()

    }

    private fun initView(){
        contactsPendingAdapter = ContactsPendingAdapter()
        binding?.let {
            it.rvPendingContacts.adapter = contactsPendingAdapter
            it.rvPendingContacts.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initListener(){

    }

    private fun initObservers(){
        viewModel.contactsToAccept.observe(viewLifecycleOwner,{
            when(it.status){
                StatusResult.LOADING -> initDialog()
                StatusResult.OK -> {
                    contactsPendingAdapter?.submitList(it.data)
                }
                StatusResult.BAD -> requireContext().toastShort(it.message)
            }
        })

        viewModel.dataLoading.observe(viewLifecycleOwner,{
            if (!it){
                dialogLoading.dismiss()
            }
        })
    }

    private fun initDialog(){
        viewModel.setLoadingTrue()
        if (!dialogLoading.isAdded){
            dialogLoading.show(parentFragmentManager,LoadingDialogFragment.TAG)
        }

    }

    companion object {
        val TAG: String = PendingContactFragment::class.java.simpleName
        fun newInstance() = PendingContactFragment()
    }

}