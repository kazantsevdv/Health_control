package com.kazantsev.healthcontrol.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.kazantsev.healthcontrol.model.HealthItem

interface FirestoreRepository {
    fun saveItem(healthItem: HealthItem): Task<Void>
    fun getSavedItems(): CollectionReference
    fun deleteItem(item: HealthItem): Task<Void>

}
