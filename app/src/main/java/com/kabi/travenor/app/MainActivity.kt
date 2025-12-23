package com.kabi.travenor.app

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kabi.travenor.app.db.BookingViewModel
import com.kabi.travenor.app.navigation.NavigationRoot
import com.kabi.travenor.core.presentation.design_system.theme.TravenorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )
        setContent {
            TravenorTheme {
                NavigationRoot(
                    viewModel = viewModel<BookingViewModel>()
                )
            }
        }
    }
}


/**
 * // root of firestore can only contain collections
 * // collection contain docs
 * // docs < 1MB
 * // docs cannot contain another docs
 * // docs can points to sub collections
 *
 * // firestore.collection(...).document(...)
 * //      .collection(...).document(...)
 * //  .collection(...).document(...)...
 * */
