package com.kazantsev.healthcontrol.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class HealthViewModel @Inject constructor(
//    private val repo: TimetableRepo,
//    private val dateUtil: DateUtil
) : ViewModel() {


    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val viewModerProvider: Provider<HealthViewModel>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == HealthViewModel::class.java)
            return viewModerProvider.get() as T
        }
    }
}