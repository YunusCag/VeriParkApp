package com.yunuscagliyan.veriparkapp.presentation.home.view_holder

import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.yunuscagliyan.veriparkapp.R
import com.yunuscagliyan.veriparkapp.common.extension.toDecrypted
import com.yunuscagliyan.veriparkapp.data.remote.model.response.stock.StockModel
import com.yunuscagliyan.veriparkapp.databinding.TableStockRowBinding
import java.text.DecimalFormat

class TableStockViewHolder(
    private val binding: TableStockRowBinding,
    private val getDecrypted:(text:String)->String,
    ) : RecyclerView.ViewHolder(binding.root) {


    fun bind(stock: StockModel, index: Int) {
        val decimalFormat = DecimalFormat("#.##")
        binding.apply {

            val rowColor= if (index % 2 == 1) {
                ContextCompat.getColor(
                itemView.context,
                R.color.tableZebraColor,
                )
            }else{
                ContextCompat.getColor(
                    itemView.context,
                    R.color.white,
                )
            }
            containerRow.setBackgroundColor(
                rowColor
            )
            tvSymbol.text = getDecrypted(stock.symbol?:"")
            tvPrice.text = decimalFormat.format(stock.price ?: 0)
            tvDifference.text = decimalFormat.format(stock.difference ?: 0)
            tvVolume.text = decimalFormat.format(stock.volume ?: 0)
            tvOffer.text = decimalFormat.format(stock.offer ?: 0)
            tvBid.text = decimalFormat.format(stock.bid ?: 0)


            if (stock.isUp == true) {
                ivChanging.setImageResource(R.drawable.ic_arrow_up)
                DrawableCompat.setTint(
                    DrawableCompat.wrap(ivChanging.drawable),
                    ContextCompat.getColor(itemView.context, R.color.green_500)
                )
            }
            if (stock.isDown == true) {
                ivChanging.setImageResource(R.drawable.ic_arrow_down)
                DrawableCompat.setTint(
                    DrawableCompat.wrap(ivChanging.drawable),
                    ContextCompat.getColor(itemView.context, R.color.red_500)
                )
            }
        }
    }
}