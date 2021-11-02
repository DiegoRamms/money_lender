package com.appgame.prestador.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.appgame.prestador.domain.login.LoginRequest
import com.appgame.prestador.databinding.FragmentLoginBinding
import com.appgame.prestador.utils.LoadingDialogFragment
import com.appgame.prestador.domain.StatusResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    private var dialogLoading: LoadingDialogFragment? = null
    private val viewModel: LoginViewModel by viewModels()
    private var callback: ClickLogin? = null
    private var isLogout = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        isLogout = requireArguments().getBoolean("IS_LOG_OUT",false)

        binding?.edtUser?.setText("diego1@gmail.com")
        binding?.edtPassword?.setText("123442332342345")

        binding?.btnLogin?.setOnClickListener {
            viewModel.login(
                LoginRequest(
                    binding?.edtUser?.text.toString(),
                    binding?.edtPassword?.text.toString(),
                    "MONEY_LENER"
                )
            )
        }
        if (isLogout) viewModel.logout()

    }

    private fun initObservers() {
        viewModel.loginResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                StatusResult.LOADING -> {
                    initDialog()
                }
                StatusResult.OK -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    dialogLoading?.dismiss()
                    callback?.onClickLogin()
                }
                StatusResult.BAD -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    dialogLoading?.dismiss()
                }
            }

        })
        viewModel.logoutResponse.observe(viewLifecycleOwner,{
            when (it.status) {
                StatusResult.LOADING -> {
                    initDialog()
                }
                StatusResult.OK -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    dialogLoading?.dismiss()
                }
                StatusResult.BAD -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    dialogLoading?.dismiss()
                }
            }
        })
    }

    private fun initDialog(){
        dialogLoading = LoadingDialogFragment.newInstance()
        dialogLoading?.show(parentFragmentManager,LoadingDialogFragment.TAG)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = context as ClickLogin
        }catch (e: ClassCastException){}

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        val TAG: String = LoginFragment::class.java.simpleName
        fun getInstance() = LoginFragment()
    }

    interface ClickLogin{
        fun onClickLogin()
    }

}