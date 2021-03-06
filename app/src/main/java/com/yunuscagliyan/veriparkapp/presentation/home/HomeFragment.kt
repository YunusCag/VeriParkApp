package com.yunuscagliyan.veriparkapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.common.extension.onQueryTextChanged
import com.yunuscagliyan.veriparkapp.common.extension.toEncrypted
import com.yunuscagliyan.veriparkapp.data.remote.model.response.stock.StockModel
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
    private var navController:NavController?=null


    private var stockList=ArrayList<StockModel?>()

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
        navController=Navigation.findNavController(view)

        initUI()
        listenObserver()
    }

    private fun initUI() {
        stockAdapter = TableStockAdapter(){stock->
            val action=HomeFragmentDirections.actionHomeDestinationToDetailDestination(
                mainViewModel.getEncryptedText(stock?.id?.toString()?:"")
            )
            navController?.navigate(action)

        }

        binding?.apply {
            (activity as? MainActivity)?.setUpToolbar(toolBar)
            rvStock.setHasFixedSize(true)
            rvStock.adapter = stockAdapter

            etSearch.setText(viewModel.searchQuery.value)

            etSearch.onQueryTextChanged{search->
                viewModel.searchQuery.value=search
            }
        }
    }

    private fun listenObserver() {
        mainViewModel.period.observe(viewLifecycleOwner) {
            val periodEncrypted = mainViewModel.getEncryptedText(it.period)
            viewModel.getStock(periodEncrypted)
            binding?.etSearch?.setText("")
        }

        viewModel.searchQuery.observe(viewLifecycleOwner){search->
            filterStockBySymbol(search)
        }

        viewModel.stocks.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {
                    binding?.apply {
                        layoutSuccess.visibility=View.GONE
                        layoutLoading.container.visibility=View.GONE
                        layoutError.container.visibility=View.VISIBLE
                        layoutError.tvError.text=resource.message?:""
                    }
                }
                is Resource.Loading -> {
                    binding?.apply {
                        layoutSuccess.visibility=View.GONE
                        layoutLoading.container.visibility=View.VISIBLE
                        layoutError.container.visibility=View.GONE
                    }
                }
                is Resource.Success -> {
                    val stocks = resource.data
                    stocks?.let {
                        this.stockList.clear()
                        this.stockList.addAll(it)
                        this.stockList.forEachIndexed{index,stock->
                            this.stockList[index]?.symbol=mainViewModel.getDecryptedText(stock?.symbol?:"")
                        }
                        filterStockBySymbol("")
                    }
                    binding?.apply {
                        layoutLoading.container.visibility=View.GONE
                        layoutError.container.visibility=View.GONE
                        layoutSuccess.visibility=View.VISIBLE
                    }
                }
            }
        }
    }

    private fun filterStockBySymbol(search: String) {
        val newList = stockList.filter { stock ->
            val symbol = stock?.symbol ?: ""
            symbol.contains(search, true)
        }
        stockAdapter.submitList(newList)
    }
}