package com.kabi.travenor.user_flow.sign_in.use_cases

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
