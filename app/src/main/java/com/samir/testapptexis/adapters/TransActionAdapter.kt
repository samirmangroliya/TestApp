package com.samir.testapptexis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.samir.testapptexis.R
import com.samir.testapptexis.data.model.TransactionModel
import com.samir.testapptexis.databinding.ItemTransactionBinding

class TransActionAdapter(val data: ArrayList<TransactionModel>) :
    RecyclerView.Adapter<TransActionAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_transaction,
            FrameLayout(parent.context), false
        )
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        try {
            data.let {
                holder.setData(it[position])
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onViewAttachedToWindow(holder: DataViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: DataViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

    fun addItemForTest(transactionModel: TransactionModel) {
        try {
            data.add(0, transactionModel)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class DataViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private var binding: ItemTransactionBinding? = null
        fun bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView)
            }
        }

        fun unbind() {
            binding?.unbind()
        }

        fun setData(transactionModel: TransactionModel?) {
            try {
                transactionModel?.let {
                    binding?.transactionModel = it
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        init {
            bind()
        }
    }

}