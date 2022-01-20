package com.kotlin.basicstructure.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.basicstructure.R
import com.kotlin.basicstructure.data.model.Photo
import com.kotlin.basicstructure.databinding.ItemProductBinding
import com.kotlin.basicstructure.util.clickWithDebounce


class UserAdapter(
    private var userList: ArrayList<Photo>,
    private var clickInterface : ClickListener
) : RecyclerView.Adapter<UserAdapter.DataViewHolder>() {

    interface ClickListener {
        fun itemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.binding.imgBanner.clickWithDebounce {
            clickInterface.itemClick(position)
        }
    }

    class DataViewHolder(var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: Photo) = binding.apply {
            mData = itemData
            executePendingBindings()
        }
    }

    fun setData(list: List<Photo>, loadMore: Boolean) {
        val oldListSize = this.userList.size
        if (!loadMore) {
            userList.clear()
            userList.addAll(list)
        } else {
            userList.addAll(list)
        }
        notifyItemRangeChanged(oldListSize, list.size)
    }


}