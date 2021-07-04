package com.kazantsev.healthcontrol.ui.model

sealed class DataItem {
    data class Item(
        val time: String,
        val pressUp: String,
        val pressDown: String,
        val pulse: String,
        val isWarning: String
    ) : DataItem()

    data class Header(
        val Date: String
    ) : DataItem()
}