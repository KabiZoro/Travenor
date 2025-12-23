package com.kabi.travenor.user_flow.sign_in.use_cases

import android.util.Patterns

fun validateEmail(email: String): ValidationResult {
    if (email.isBlank()) {
        return ValidationResult(
            successful = false,
            errorMessage = "Email cannot be blank"
        )
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return ValidationResult(
            successful = false,
            errorMessage = "That's not a valid email"
        )
    }
    return ValidationResult(
        successful = true
    )
}
