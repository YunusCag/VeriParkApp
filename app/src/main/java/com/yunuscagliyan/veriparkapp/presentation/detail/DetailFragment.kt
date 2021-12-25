package com.yunuscagliyan.veriparkapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yunuscagliyan.veriparkapp.R
import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.data.remote.model.response.detail.StockDetailModel
import com.yunuscagliyan.veriparkapp.databinding.FragmentDetailBinding
import com.yunuscagliyan.veriparkapp.presentation.MainActivity
import com.yunuscagliyan.veriparkapp.presentation.MainViewModel
import com.yunuscagliyan.veriparkapp.presentation.detail.view_model.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class DetailFragment : Fragment() {


    private var binding:FragmentDetailBinding?=null

    private val viewModel:DetailViewModel by viewModels()
    private val mainViewModel:MainViewModel by activityViewModels()


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
                    resource.data?.let {
                        bindDetail(it)
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
    private fun bindDetail(detail: StockDetailModel) {
        val decimalFormat=DecimalFormat("#.##")

        binding?.apply {
            layoutFirst.apply {
                tvLeft.text=getString(R.string.detail_page_symbol,mainViewModel.getDecryptedText(detail.symbol?:""))
                tvRight.text=getString(R.string.detail_page_daily_lowest, decimalFormat.format(detail.lowest?:0))
            }
            layoutSecond.apply {
                tvLeft.text=getString(R.string.detail_page_price,decimalFormat.format(detail.price?:0))
                tvRight.text=getString(R.string.detail_page_daily_highest,decimalFormat.format(detail.highest?:0))
            }
            layoutThird.apply {
                tvLeft.text=getString(R.string.detail_page_difference,decimalFormat.format(detail.difference?:0))
                tvRight.text=getString(R.string.detail_page_count,detail.count?:0)
            }
            layoutFourth.apply {
                tvLeft.text=getString(R.string.detail_page_volume,decimalFormat.format(detail.volume?:0))
                tvRight.text=getString(R.string.detail_page_minimum,decimalFormat.format(detail?.maximum?:0))
            }
            layoutFifth.apply {
                tvLeft.text=getString(R.string.detail_page_bid,decimalFormat.format(detail.bid?:0))
                tvRight.text=getString(R.string.detail_page_maximum,decimalFormat.format(detail.minimum?:0))
            }
            layoutSixth.apply {
                tvLeft.text=getString(R.string.detail_page_offer,decimalFormat.format(detail.offer?:0))
                tvRight.text=getString(R.string.detail_page_change)
                ivChanging.visibility=View.VISIBLE
                if (detail.isUp == true) {
                    ivChanging.setImageResource(R.drawable.ic_arrow_up)
                    DrawableCompat.setTint(
                        DrawableCompat.wrap(ivChanging.drawable),
                        ContextCompat.getColor(requireContext(), R.color.green_500)
                    )
                }
                if (detail.isDown == true) {
                    ivChanging.setImageResource(R.drawable.ic_arrow_down)
                    DrawableCompat.setTint(
                        DrawableCompat.wrap(ivChanging.drawable),
                        ContextCompat.getColor(requireContext(), R.color.red_500)
                    )
                }
            }
        }
    }
}