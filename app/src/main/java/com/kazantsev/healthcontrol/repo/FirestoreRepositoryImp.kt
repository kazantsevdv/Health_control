package com.kazantsev.healthcontrol.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.kazantsev.healthcontrol.model.HealthItem

class FirestoreRepositoryImp : FirestoreRepository {

    var firestoreDB = FirebaseFirestore.getInstance()


    override fun saveItem(healthItem: HealthItem): Task<Void> {

        val documentReference =
            firestoreDB.collection("Health").document(healthItem.date.toString())
        return documentReference.set(healthItem)
    }


    override fun getSavedItems(): CollectionReference {
        return firestoreDB.collection("Health")
    }


    override fun deleteItem(item: HealthItem): Task<Void> {
        val documentReference = firestoreDB.collection("Health")
            .document(item.date.toString())

        return documentReference.delete()
    }

}