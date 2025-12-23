package com.kabi.travenor.user_flow.verification

sealed interface VerificationAction {
    data class OnEnterNumber(val number: Int?, val index: Int) : VerificationAction
    data class OnChangeFieldFocused(val index: Int) : VerificationAction
    data object OnKeyboardBack : VerificationAction
}