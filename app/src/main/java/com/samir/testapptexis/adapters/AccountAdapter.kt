package com.samir.testapptexis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.samir.testapptexis.R
import com.samir.testapptexis.data.model.UserAccountModel
import com.samir.testapptexis.databinding.ItemAccountBinding
import com.samir.testapptexis.ui.DashBoardActivity

class AccountAdapter(val activity: DashBoardActivity, val data: List<UserAccountModel>): RecyclerView.Adapter<AccountAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_account,
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


    inner class DataViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private var binding: ItemAccountBinding? = null
        fun bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView)
            }
        }

        fun unbind() {
            binding?.unbind()
        }

        fun setData(userAccountModel: UserAccountModel?) {
            try {
                userAccountModel?.let {
                    binding?.userAccount= it
                    binding?.activityDashBoard=activity
                    binding?.executePendingBindings()
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