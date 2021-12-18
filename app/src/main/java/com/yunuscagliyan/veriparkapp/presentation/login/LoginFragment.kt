package com.yunuscagliyan.veriparkapp.presentation.login

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yunuscagliyan.veriparkapp.databinding.FragmentLoginBinding
import com.yunuscagliyan.veriparkapp.presentation.login.view_model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class LoginFragment : Fragment() {


    private var binding:FragmentLoginBinding?=null
    private val viewModel:LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLoginBinding.inflate(layoutInflater,container,false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.handShake.observe(viewLifecycleOwner){resources->

        }
    }

}