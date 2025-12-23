package com.kabi.travenor.user_flow.verification.util

fun Int.toCountdownTimer(): String{
    val minutes = this / 60
    val seconds = this % 60
    return String.format("%02d:%02d", minutes, seconds)
}