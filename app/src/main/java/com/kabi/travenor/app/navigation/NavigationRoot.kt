package com.kabi.travenor.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.kabi.travenor.user_flow.on_board.OnBoardScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.kabi.travenor.R
import com.kabi.travenor.app.db.BookingViewModel
import com.kabi.travenor.owner_flow.OwnerDetailScreen
import com.kabi.travenor.owner_flow.OwnerHomeScreen
import com.kabi.travenor.user_flow.main_screen.DetailScreen
import com.kabi.travenor.user_flow.main_screen.HomeScreen
import com.kabi.travenor.user_flow.main_screen.models.Place
import com.kabi.travenor.user_flow.sign_in.SignInScreen
import com.kabi.travenor.user_flow.verification.VerificationRoot
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlin.collections.listOf

@Composable
fun NavigationRoot(
    viewModel: BookingViewModel,
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.OnBoardScreen::class, Route.OnBoardScreen.serializer())
                    subclass(Route.SignInScreen::class, Route.SignInScreen.serializer())
                    subclass(Route.VerificationScreen::class, Route.VerificationScreen.serializer())
                    subclass(Route.HomeScreen::class, Route.HomeScreen.serializer())
                    subclass(Route.DetailScreen::class, Route.DetailScreen.serializer())
                    subclass(Route.OwnerHomeScreen::class, Route.OwnerHomeScreen.serializer())
                    subclass(Route.OwnerDetailScreen::class, Route.OwnerDetailScreen.serializer())
                }
            }
        },
        Route.OnBoardScreen
    )
    val resultStore = rememberResultStore()
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.OnBoardScreen> {
                OnBoardScreen(
                    onNavigateToSignInScreen = {
                        backStack.add(Route.SignInScreen)
                    }
                )
            }
            entry<Route.SignInScreen> {
                SignInScreen(
                    resultStore = resultStore,
                    onNavigateToOnBoardScreen = {
                        backStack.remove(Route.SignInScreen)
                    },
                    onNavigateToVerificationScreen = {
                        backStack.add(Route.VerificationScreen)
                    }
                )
            }
            entry<Route.VerificationScreen> {
                VerificationRoot(
                    resultStore = resultStore,
                    onNavigateToSignInScreen = {
                        backStack.remove(Route.VerificationScreen)
                    },
                    onNavigateToHomeScreen = {
                        backStack.remove(Route.VerificationScreen)
                        backStack.add(Route.HomeScreen)
                    },
                    onNavigateToOwnerHomeScreen = {
                        backStack.remove(Route.VerificationScreen)
                        backStack.add(Route.OwnerHomeScreen)
                    },
                )
            }
            entry<Route.HomeScreen> {
                HomeScreen(
                    resultStore = resultStore,
                    onNavigateToDetailScreen = {
                        backStack.add(Route.DetailScreen)
                    }
                )
            }
            entry<Route.DetailScreen> {
                DetailScreen(
                    resultStore = resultStore,
                    viewModel = viewModel,
                    onNavigateToHomeScreen = {
                        backStack.remove(Route.DetailScreen)
                    }
                )
            }
            entry<Route.OwnerHomeScreen> {
                OwnerHomeScreen(
                    resultStore = resultStore,
                    onNavigateToOwnerDetailScreen = {
                        backStack.add(Route.OwnerDetailScreen)
                    }
                )
            }
            entry<Route.OwnerDetailScreen> {
                OwnerDetailScreen(
                    resultStore = resultStore,
                    viewModel = viewModel
                )
            }
        }
    )

}