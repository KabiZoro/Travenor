package com.kabi.travenor.app.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import com.kabi.travenor.user_flow.on_board.OnBoardScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.kabi.travenor.app.db.BookingViewModel
import com.kabi.travenor.owner_flow.OwnerDetailScreen
import com.kabi.travenor.owner_flow.OwnerHomeScreen
import com.kabi.travenor.user_flow.main_screen.DetailScreen
import com.kabi.travenor.user_flow.main_screen.HomeScreen
import com.kabi.travenor.user_flow.sign_in.SignInScreen
import com.kabi.travenor.user_flow.verification.VerificationRoot
import kotlin.collections.listOf

@Composable
fun NavigationRoot(
    viewModel: BookingViewModel,
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(Route.OnBoardScreen)
    val resultStore = rememberResultStore()
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        transitionSpec = {
            slideInHorizontally{ it } + fadeIn() togetherWith
                    slideOutHorizontally{ -it } + fadeOut()
        },
        predictivePopTransitionSpec = {
            slideInHorizontally{ -it } + fadeIn() togetherWith
                    slideOutHorizontally{ it } + fadeOut()
        },
        popTransitionSpec = {
            slideInHorizontally{ -it } + fadeIn() togetherWith
                    slideOutHorizontally{ it } + fadeOut()
        },
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