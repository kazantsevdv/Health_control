package com.kazantsev.healthcontrol.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepository {

    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()


//
//    // save address to firebase
//    fun saveAddressItem(addressItem: AddressItem): Task<Void> {
//        //var
//        val documentReference = firestoreDB.collection("users").document(user!!.email.toString())
//            .collection("saved_addresses").document(addressItem.addressId)
//        return documentReference.set(addressItem)
//    }
//
//    // get saved addresses from firebase
//    fun getSavedAddress(): CollectionReference {
//        val collectionReference = firestoreDB.collection("users/${user!!.email.toString()}/saved_addresses")
//        return collectionReference
//    }
//
//    fun deleteAddress(addressItem: AddressItem): Task<Void> {
//        val documentReference =  firestoreDB.collection("users/${user!!.email.toString()}/saved_addresses")
//            .document(addressItem.addressId)
//
//        return documentReference.delete()
//    }

}