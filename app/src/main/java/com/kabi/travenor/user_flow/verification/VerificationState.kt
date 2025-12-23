package com.kabi.travenor.user_flow.verification

data class VerificationState(
    val code: List<Int?> = (1..4).map { null },
    val focusedIndex: Int? = null,
    val isValid: Boolean? = null,
    val timeLeft: Int = 90
)