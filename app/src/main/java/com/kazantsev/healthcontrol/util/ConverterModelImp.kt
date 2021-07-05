package com.kazantsev.healthcontrol.util

import com.kazantsev.healthcontrol.R
import com.kazantsev.healthcontrol.model.HealthItem
import com.kazantsev.healthcontrol.ui.model.DataItem
import java.text.SimpleDateFormat
import java.util.*

class ConverterModelImp : ConverterModel {
    private val dateFormat = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())


    override fun getDateStr(date: Date): String = dateFormat.format(date)

    override fun getTimeStr(date: Date): String = timeFormat.format(date)

    override fun fireBaseToUi(item: HealthItem): DataItem.Item {
        return DataItem.Item(
            item.date,
            getTimeStr(item.date),
            item.presUp.toString(),
            item.presDown.toString(),
            item.pulse.toString(),
            getItemColor(item)
        )
    }

    override fun uiToFirebase(item: DataItem.Item): HealthItem {
        return HealthItem(
            item.date,
            item.pressUp.toInt(),
            item.pressDown.toInt(),
            item.pulse.toInt()
        )
    }

    private fun getItemColor(item: HealthItem): Int {
        return if (
            item.presUp in 100..129 && item.presDown in 60..84
        )
            R.drawable.green_gradient
        else
            R.drawable.yellow_gradient
    }

}