package com.yunuscagliyan.veriparkapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yunuscagliyan.veriparkapp.R
import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.databinding.FragmentDetailBinding
import com.yunuscagliyan.veriparkapp.presentation.MainActivity
import com.yunuscagliyan.veriparkapp.presentation.detail.view_model.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {


    private var binding:FragmentDetailBinding?=null

    private val viewModel:DetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        listenObserver()

    }

    private fun initUI() {
        binding?.apply {
            (activity as? MainActivity)?.setUpToolbar(toolBar)
        }
    }
    private fun listenObserver() {
        viewModel.stockDetail.observe(viewLifecycleOwner){resource->
            when(resource){
                is Resource.Error ->{
                    binding?.apply {
                        layoutSuccess.visibility=View.GONE
                        layoutLoading.container.visibility=View.GONE
                        layoutError.container.visibility=View.VISIBLE
                        layoutError.tvError.text=resource.message?:""
                    }
                }
                is Resource.Loading ->{
                    binding?.apply {
                        layoutSuccess.visibility=View.GONE
                        layoutLoading.container.visibility=View.VISIBLE
                        layoutError.container.visibility=View.GONE
                    }
                }
                is Resource.Success ->{

                    binding?.apply {
                        layoutLoading.container.visibility=View.GONE
                        layoutError.container.visibility=View.GONE
                        layoutSuccess.visibility=View.VISIBLE
                    }
                }
            }
        }
    }
}