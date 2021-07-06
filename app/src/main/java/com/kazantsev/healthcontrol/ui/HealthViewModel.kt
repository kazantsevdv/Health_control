package com.kazantsev.healthcontrol.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.EventListener
import com.kazantsev.healthcontrol.model.HealthItem
import com.kazantsev.healthcontrol.repo.FirestoreRepository
import com.kazantsev.healthcontrol.ui.model.DataItem
import com.kazantsev.healthcontrol.util.ConverterModel
import javax.inject.Inject
import javax.inject.Provider

class HealthViewModel @Inject constructor(
    private val repo: FirestoreRepository,
    private val converterModel: ConverterModel
) : ViewModel() {
    private var _items: MutableLiveData<List<DataItem>> = MutableLiveData()
    val item: LiveData<List<DataItem>> get() = _items

    private var _err: MutableLiveData<String> = MutableLiveData()
    val err: LiveData<String> get() = _err


    init {
        getSavedItems()
    }

    fun saveItem(item: HealthItem) {
        repo.saveItem(item).addOnFailureListener {
            _err.value=it.localizedMessage
        }
    }

    private fun getSavedItems() {
        repo.getSavedItems()
            .orderBy("date")
            .addSnapshotListener(EventListener { value, e ->
                if (e != null) {
                    _items.value = null
                    _err.value=e.localizedMessage
                    return@EventListener
                }

                val savedItemsList: MutableList<DataItem> = mutableListOf()
                var prevDate = ""
                for (doc in value!!) {
                    val item = doc.toObject(HealthItem::class.java)
                    val curDate = converterModel.getDateStr(item.date)
                    if (prevDate != curDate) {
                        savedItemsList.add(DataItem.Header(curDate))
                        prevDate = curDate
                    }
                    val addressItem = converterModel.fireBaseToUi(item)
                    savedItemsList.add(addressItem)
                }
                _items.value = savedItemsList
            })


    }

    fun deleteItem(item: DataItem) {
        if (item is DataItem.Item) {
            repo.deleteItem(converterModel.uiToFirebase(item = item)).addOnFailureListener {
                _err.value=it.localizedMessage
            }
        }
    }

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