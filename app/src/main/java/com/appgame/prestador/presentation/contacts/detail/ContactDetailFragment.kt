package com.appgame.prestador.presentation.contacts.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.appgame.prestador.databinding.FragmentContactDetailBinding
import com.appgame.prestador.domain.StatusResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.contact.IdContactRequest
import com.appgame.prestador.presentation.contacts.adapter.ContactsAdapter
import com.appgame.prestador.utils.CONTACT
import com.appgame.prestador.utils.ErrorDialogFragment
import com.appgame.prestador.utils.LoadingDialogFragment
import com.appgame.prestador.utils.toastLong
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class ContactDetailFragment : Fragment() {

    private var _binding: FragmentContactDetailBinding? = null
    private val binding get() = _binding
    private val contactsAdapter = ContactsAdapter()
    private var contact: Contact? = null
    private val dialogLoading: LoadingDialogFragment by lazy { LoadingDialogFragment.newInstance() }
    private val dialogError: ErrorDialogFragment by lazy { ErrorDialogFragment.newInstance() }
    private val viewModel: ContactDetailViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contact = requireArguments().getParcelable(CONTACT)
        initView()
        initObservers()

    }

    private fun initView() {

        contact?.let {
            binding?.tvLetterBig?.text = it.name[0].toString()
            binding?.tvLetterMini?.text = it.name[0].toString()
            it.idContact?.let { id ->  viewModel.getLoansByContactId(IdContactRequest(id))}

        }
        binding?.tvCount?.text = "Prestamos: 0"
        binding?.rvLoan?.let {
            it.adapter = contactsAdapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }
        binding?.appBarLayout?.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (appBarLayout.totalScrollRange.toFloat() == abs(verticalOffset.toFloat()))
                    initAnimationBar()
                else if (appBarLayout.totalScrollRange.toFloat() / 2 > verticalOffset.toFloat())
                    hideItemsToolBar()
            })
    }

    private fun initObservers(){
        viewModel.loan.observe(viewLifecycleOwner,{
            when(it.status){
                StatusResult.LOADING -> initDialog()
                StatusResult.OK -> requireContext().toastLong(it.data.toString())
                StatusResult.BAD -> initDialogError(it.message)
            }
        })

        viewModel.dialogLoading.observe(viewLifecycleOwner, {
            if (!it) dialogLoading.dismiss()
        })
    }

    companion object {
        val TAG: String = ContactDetailFragment::class.java.simpleName
        fun newInstance() = ContactDetailFragment()
    }


    private fun initAnimationBar() {
        binding?.toolbar?.elevation = 0.5f
        binding?.flMini?.apply {
            visibility = View.VISIBLE
            alpha = 0f
            x = -500f
            rotation = 0f
            animate().alpha(1f).translationX(-50f).setDuration(1500).rotationBy(360f).start()
        }
        binding?.tvName?.apply {
            x = -500f
            visibility = View.VISIBLE
            animate()?.translationX(-50f)?.setDuration(1000)?.start()
        }
        binding?.imgSearch?.apply {
            visibility = View.VISIBLE
            alpha = 0f
            animate().alpha(1f).setDuration(500).start()
        }
    }

    private fun hideItemsToolBar() {
        binding?.toolbar?.elevation = 0f
        binding?.flMini?.apply {
            rotation = 0f
            animate().alpha(0f).translationX(-500f).setDuration(1500).rotationBy(360f)
                .withEndAction {
                    visibility = View.GONE
                }.start()
        }
        binding?.tvName?.apply {
            animate()?.translationX(-500f)?.setDuration(1000)?.withEndAction {
                visibility = View.GONE
            }?.start()
        }
        binding?.imgSearch?.apply {
            visibility = View.GONE
            animate().alpha(0f).setDuration(500).start()
        }
    }

    private fun initDialogError(message: String) {
        dialogError.showDialog(parentFragmentManager, message)
    }

    private fun initDialog() {
        viewModel.setDialogLoadingTrue()
        dialogLoading.showDialog(parentFragmentManager)
    }

}