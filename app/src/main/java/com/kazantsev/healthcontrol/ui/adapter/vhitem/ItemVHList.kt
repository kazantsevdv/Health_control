package com.kazantsev.healthcontrol.ui.adapter.vhitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.kazantsev.healthcontrol.ui.adapter.BaseViewHolder
import com.kazantsev.healthcontrol.ui.model.DataItem

interface ItemVHList<V : ViewBinding, I : DataItem> {

    fun isRelativeItem(item: DataItem): Boolean

    @LayoutRes
    fun getLayoutId(): Int

    fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<V, I>
    fun getDiffUtil(): DiffUtil.ItemCallback<I>
}