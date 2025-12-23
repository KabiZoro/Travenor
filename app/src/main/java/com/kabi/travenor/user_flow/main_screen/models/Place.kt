package com.kabi.travenor.user_flow.main_screen.models

data class Place(
    val id: String = "",
    val guestId: String = "",
    val ownerId: String = "",
    val image: Int = 0,
    val name: String = "",
    val description: String = "",
    val location: String = "",
    val status: String = "IDLE"
)