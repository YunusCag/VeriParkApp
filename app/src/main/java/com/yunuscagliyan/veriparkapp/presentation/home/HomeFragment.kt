package com.yunuscagliyan.veriparkapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yunuscagliyan.veriparkapp.R
import com.yunuscagliyan.veriparkapp.common.extension.toEncrypted
import com.yunuscagliyan.veriparkapp.databinding.FragmentHomeBinding
import com.yunuscagliyan.veriparkapp.presentation.MainActivity
import com.yunuscagliyan.veriparkapp.presentation.MainViewModel
import com.yunuscagliyan.veriparkapp.presentation.home.view_model.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding:FragmentHomeBinding?=null
    private val viewModel:HomeViewModel by viewModels()
    private val mainViewModel:MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        listenObserver()
    }

    private fun listenObserver() {
        mainViewModel.period.observe(viewLifecycleOwner){
            val periodEncrypted=it.period.toEncrypted(
                mainViewModel.aesKey,
                mainViewModel.aesIV,
            )
            viewModel.getStock(periodEncrypted)
        }
    }

    private fun initUI() {
        binding?.let {
            (activity as? MainActivity)?.setUpToolbar(it.toolBar)
        }
    }
}