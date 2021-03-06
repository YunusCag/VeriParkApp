package com.yunuscagliyan.veriparkapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yunuscagliyan.veriparkapp.data.remote.model.response.stock.StockModel
import com.yunuscagliyan.veriparkapp.databinding.TableStockRowBinding
import com.yunuscagliyan.veriparkapp.presentation.home.view_holder.TableStockViewHolder

class TableStockAdapter(
    private val onRowClick: (stock: StockModel?) -> Unit,
) : RecyclerView.Adapter<TableStockViewHolder>() {

    private val stocks: ArrayList<StockModel?> = ArrayList<StockModel?>()

    fun submitList(stockList: List<StockModel?>) {
        this.stocks.clear()
        this.stocks.addAll(stockList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableStockViewHolder {
        val binding =
            TableStockRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TableStockViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TableStockViewHolder, position: Int) {
        val stock = this.stocks[position]
        stock?.let {
            holder.bind(it, position, onRowClick)
        }
    }

    override fun getItemCount(): Int {
        return this.stocks.size
    }
}