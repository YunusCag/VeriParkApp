package com.yunuscagliyan.veriparkapp.presentation.login

import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yunuscagliyan.veriparkapp.R
import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.common.extension.toEncrypted
import com.yunuscagliyan.veriparkapp.databinding.FragmentLoginBinding
import com.yunuscagliyan.veriparkapp.presentation.MainViewModel
import com.yunuscagliyan.veriparkapp.presentation.login.view_model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.Exception
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@AndroidEntryPoint
class LoginFragment : Fragment() {


    private var binding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModel by viewModels()
    private var navController: NavController? = null
    private val mainViewModel: MainViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initUI()
    }

    private fun initUI() {
        binding?.apply {
            layoutLoading.container.visibility=View.GONE

            btnLogin.setOnClickListener {
                viewModel.onLoginClick()
                navController = Navigation.findNavController(it)
            }
        }
    }

    private fun initObservers() {
        viewModel.handShake.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding?.apply {
                        layoutLogin.visibility=View.GONE
                        layoutLoading.container.visibility=View.VISIBLE
                    }
                }
                is Resource.Success -> {
                    resource.data?.let { handShake ->
                        mainViewModel.aesKey=handShake.aesKey ?: ""
                        mainViewModel.aesIV=handShake.aesIV ?: ""

                        viewModel.saveAuthToken(handShake.authorization?:"")
                        navController?.navigate(R.id.action_login_destination_to_home_destination)

                    }
                }
                is Resource.Error -> {
                    binding?.apply {
                        btnLogin.visibility=View.VISIBLE
                        layoutLoading.container.visibility=View.GONE
                    }
                    Timber.e(resource.message)
                }
            }
        }
    }


}