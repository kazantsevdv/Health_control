package com.kazantsev.healthcontrol.util

import com.kazantsev.healthcontrol.model.HealthItem
import com.kazantsev.healthcontrol.ui.model.DataItem
import java.util.*

interface ConverterModel {
    fun getDateStr(date: Date): String
    fun getTimeStr(date: Date): String
    fun fireBaseToUi(item: HealthItem): DataItem.Item
    fun uiToFirebase(item: DataItem.Item):HealthItem
}
