package com.kazantsev.healthcontrol.ui.model

import androidx.annotation.DrawableRes
import java.util.*

sealed class DataItem {
    data class Item(
        val date: Date,
        val time: String,
        val pressUp: String,
        val pressDown: String,
        val pulse: String,
        @DrawableRes
        val color: Int
    ) : DataItem()

    data class Header(
        val date: String
    ) : DataItem()
}