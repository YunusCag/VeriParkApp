package com.yunuscagliyan.veriparkapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.common.extension.toEncrypted
import com.yunuscagliyan.veriparkapp.databinding.FragmentHomeBinding
import com.yunuscagliyan.veriparkapp.presentation.MainActivity
import com.yunuscagliyan.veriparkapp.presentation.MainViewModel
import com.yunuscagliyan.veriparkapp.presentation.home.adapter.TableStockAdapter
import com.yunuscagliyan.veriparkapp.presentation.home.view_model.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var stockAdapter: TableStockAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        listenObserver()
    }

    private fun initUI() {
        stockAdapter = TableStockAdapter {
            mainViewModel.getDecryptedText(it)
        }


        binding?.apply {
            (activity as? MainActivity)?.setUpToolbar(toolBar)
            rvStock.setHasFixedSize(true)
            rvStock.adapter = stockAdapter
        }
    }

    private fun listenObserver() {
        mainViewModel.period.observe(viewLifecycleOwner) {
            val periodEncrypted = mainViewModel.getEncryptedText(it.period)
            viewModel.getStock(periodEncrypted)
        }

        viewModel.stocks.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val stocks = resource.data
                    stocks?.let {
                        stockAdapter.submitList(it)
                    }
                }
            }
        }
    }
}