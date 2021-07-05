package com.kazantsev.healthcontrol.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class HealthItem(
    val date: Date= Date(),
    val presUp: Int=0,
    val presDown: Int=0,
    val pulse: Int=0
) : Parcelable