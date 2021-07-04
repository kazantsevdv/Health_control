package com.kazantsev.healthcontrol.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.kazantsev.healthcontrol.ui.model.DataItem

abstract class BaseViewHolder<out V : ViewBinding, in I : DataItem>(
    val binding: V
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun onBind(item: I)
}