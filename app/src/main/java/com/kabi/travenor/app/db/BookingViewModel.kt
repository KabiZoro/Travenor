package com.kabi.travenor.app.db

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.kabi.travenor.user_flow.main_screen.models.Place

class BookingViewModel : ViewModel() {

    val db = Firebase.firestore
    val auth = Firebase.auth

    // guest
    var bookingStatus = mutableStateMapOf<String, String>()

    fun createBooking(ownerId: String, placeName: String) {
        val guestId = auth.currentUser?.uid
        if (guestId != null) {
            bookingStatus[placeName] = "PENDING"
            val newBookings = hashMapOf(
                "guestId" to guestId,
                "ownerId" to ownerId,
                "name" to placeName,
                "status" to "PENDING",
                "createdAt" to FieldValue.serverTimestamp()
            )
            db.collection("bookings")
                .add(newBookings)
                .addOnSuccessListener { documentReference ->
                    listenToBookingStatus(documentReference.id, placeName)
                }
        }
    }

    private fun listenToBookingStatus(docId: String, placeName: String) {
        db.collection("bookings")
            .document(docId)
            .addSnapshotListener { snapshot, _ ->
                val status = snapshot?.getString("status") ?: "PENDING"
                bookingStatus[placeName] = status
            }
    }

    // owner
    var pendingRequests = mutableStateListOf<Place>()
    var activeRequestForDialog by mutableStateOf<Place?>(null)

    fun listenForIncomingRequests() {
        val ownerId = auth.currentUser?.uid ?: return
        db.collection("bookings")
            .whereEqualTo("ownerId", ownerId)
            .whereEqualTo("status", "PENDING")
            .addSnapshotListener { snapshots, error ->
                if (error != null) {
                    println("Firestore Error: ${error.message}")
                    return@addSnapshotListener
                }
                pendingRequests.clear()
                snapshots?.forEach { doc ->
                    val request = Place(
                        id = doc.id,
                        name = doc.getString("name") ?: "",
                        status = doc.getString("status") ?: "PENDING",
                        ownerId = doc.getString("ownerId") ?: ""
                    )
                    pendingRequests.add(request)
                }
                activeRequestForDialog = pendingRequests.firstOrNull()
            }
    }

    fun updateRequestStatus(docId: String, newStatus: String) {
        db.collection("bookings")
            .document(docId)
            .update("status", newStatus)
            .addOnSuccessListener {
                if (activeRequestForDialog?.id == docId) {
                    activeRequestForDialog = null
                }
            }
    }

}