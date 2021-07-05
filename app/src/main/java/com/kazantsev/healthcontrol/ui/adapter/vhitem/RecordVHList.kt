package com.kazantsev.healthcontrol.ui.adapter.vhitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.kazantsev.healthcontrol.R
import com.kazantsev.healthcontrol.databinding.ItemDataBinding
import com.kazantsev.healthcontrol.ui.adapter.BaseViewHolder
import com.kazantsev.healthcontrol.ui.adapter.OnListItemClickListener
import com.kazantsev.healthcontrol.ui.model.DataItem

class RecordVHList : ItemVHList<ItemDataBinding, DataItem.Item> {

    override fun isRelativeItem(item: DataItem) = item is DataItem.Item

    override fun getLayoutId() = R.layout.item_data

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemDataBinding, DataItem.Item> {
        val binding = ItemDataBinding.inflate(layoutInflater, parent, false)
        return RecordViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<DataItem.Item>() {
        override fun areItemsTheSame(oldItem: DataItem.Item, newItem: DataItem.Item) =
            oldItem.date == newItem.date &&
            oldItem.pressUp == newItem.pressUp &&
            oldItem.pressDown == newItem.pressDown &&
            oldItem.pulse == newItem.pulse


        override fun areContentsTheSame(oldItem: DataItem.Item, newItem: DataItem.Item) =
            oldItem == newItem
    }

}

class RecordViewHolder(
    binding: ItemDataBinding
) : BaseViewHolder<ItemDataBinding, DataItem.Item>(binding) {

    override fun onBind(item: DataItem.Item, onListItemClickListener: OnListItemClickListener) {
        binding.tvTime.text = item.time
        binding.tvPresHi.text = item.pressUp
        binding.tvPresLo.text = item.pressDown
        binding.tvPulse.text = item.pulse
        binding.root.background = item.getPostDrawable()
        binding.root.setOnClickListener { onListItemClickListener.onItemClick(item) }
    }

    private fun DataItem.Item.getPostDrawable() =
        ContextCompat.getDrawable(binding.root.context, color)
}
