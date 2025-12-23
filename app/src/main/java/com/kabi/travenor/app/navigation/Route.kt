package com.kabi.travenor.app.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey{

    @Serializable
    data object OnBoardScreen: Route

    @Serializable
    data object SignInScreen: Route

    @Serializable
    data object VerificationScreen: Route

    @Serializable
    data object HomeScreen: Route

    @Serializable
    data object DetailScreen: Route

    @Serializable
    data object OwnerHomeScreen: Route

    @Serializable
    data object OwnerDetailScreen: Route


}