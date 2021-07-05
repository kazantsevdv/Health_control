package com.kazantsev.healthcontrol.ui.adapter.vhitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.kazantsev.healthcontrol.R
import com.kazantsev.healthcontrol.databinding.ItemHeaderBinding
import com.kazantsev.healthcontrol.ui.adapter.BaseViewHolder
import com.kazantsev.healthcontrol.ui.adapter.OnListItemClickListener
import com.kazantsev.healthcontrol.ui.model.DataItem

class TitleVHList : ItemVHList<ItemHeaderBinding, DataItem.Header> {

    override fun isRelativeItem(item: DataItem) = item is DataItem.Header

    override fun getLayoutId() = R.layout.item_header

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemHeaderBinding, DataItem.Header> {
        val binding = ItemHeaderBinding.inflate(layoutInflater, parent, false)
        return TitleViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<DataItem.Header>() {
        override fun areItemsTheSame(oldItem: DataItem.Header, newItem: DataItem.Header) =
            oldItem.date == newItem.date

        override fun areContentsTheSame(oldItem: DataItem.Header, newItem: DataItem.Header) =
            oldItem == newItem
    }
}

class TitleViewHolder(
    binding: ItemHeaderBinding
) : BaseViewHolder<ItemHeaderBinding, DataItem.Header>(binding) {

    override fun onBind(item: DataItem.Header, onListItemClickListener: OnListItemClickListener) {
        binding.tvDate.text = item.date
    }

}
