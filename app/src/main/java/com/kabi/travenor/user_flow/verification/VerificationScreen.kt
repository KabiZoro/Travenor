@file:OptIn(ExperimentalMaterial3Api::class)

package com.kabi.travenor.user_flow.verification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kabi.travenor.core.presentation.design_system.theme.TravenorTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabi.travenor.R
import com.kabi.travenor.app.navigation.ResultStore
import com.kabi.travenor.core.presentation.design_system.button.PrimaryButton
import com.kabi.travenor.core.presentation.design_system.theme.GrayColor
import com.kabi.travenor.core.presentation.design_system.theme.SfUiDisplay
import com.kabi.travenor.core.presentation.design_system.theme.SubTextColor
import com.kabi.travenor.core.presentation.design_system.theme.TextColor
import com.kabi.travenor.user_flow.verification.components.OtpInputField
import com.kabi.travenor.user_flow.verification.util.toCountdownTimer

@Composable
fun VerificationRoot(
    resultStore: ResultStore,
    onNavigateToSignInScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOwnerHomeScreen: () -> Unit,
    viewModel: VerificationViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequesters = remember {
        (1..4).map { FocusRequester() }
    }
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current

    LaunchedEffect(state.focusedIndex) {
        state.focusedIndex?.let { index ->
            focusRequesters.getOrNull(index)?.requestFocus()
        }
    }

    LaunchedEffect(state.code, keyboardManager) {
        val allNumbersEntered = state.code.none { it == null }
        if (allNumbersEntered) {
            focusRequesters.forEach { it.freeFocus() }
            focusManager.clearFocus()
            keyboardManager?.hide()
        }
    }

    VerificationScreen(
        resultStore = resultStore,
        onNavigateToSignInScreen = onNavigateToSignInScreen,
        focusRequesters = focusRequesters,
        onNavigateToHomeScreen = onNavigateToHomeScreen,
        onNavigateToOwnerHomeScreen = onNavigateToOwnerHomeScreen,
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun VerificationScreen(
    resultStore: ResultStore,
    onNavigateToSignInScreen: () -> Unit,
    focusRequesters: List<FocusRequester>,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOwnerHomeScreen: () -> Unit,
    state: VerificationState,
    onAction: (VerificationAction) -> Unit
) {
    val email = resultStore.getResult<String>("email")
    val password = resultStore.getResult<String>("password")
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateToSignInScreen,
                        modifier = Modifier
                            .clip(RoundedCornerShape(44.dp))
                            .background(GrayColor)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChevronLeft,
                            contentDescription = null,
                            tint = TextColor,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White,
        contentWindowInsets = WindowInsets.statusBars
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .height(200.dp)
            )
            Text(
                text = stringResource(R.string.otp_verification),
                fontFamily = SfUiDisplay,
                color = TextColor,
                fontSize = 30.sp,
                lineHeight = 38.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )
            val isEmailEmpty = if (email?.isEmpty() == true) "www.uihut@gmail.com"
            else email
            Text(
                text = stringResource(
                    R.string.verification_code,
                    isEmailEmpty ?: "www.uihut@gmail.com"
                ),
                fontFamily = SfUiDisplay,
                color = SubTextColor,
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(58.dp)
            )

            // otp fields
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterHorizontally)
            ) {
                state.code.forEachIndexed { index, number ->
                    OtpInputField(
                        number = number,
                        focusRequester = focusRequesters[index],
                        onFocusChanged = { isFocused ->
                            if (isFocused) {
                                onAction(VerificationAction.OnChangeFieldFocused(index))
                            }
                        },
                        onNumberChanged = { newNumber ->
                            onAction(VerificationAction.OnEnterNumber(newNumber, index))
                        },
                        onKeyboardBack = {
                            onAction(VerificationAction.OnKeyboardBack)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(40.dp)
            )
            PrimaryButton(
                onClick = {
                    state.isValid?.let { isValid ->
                        val isValidPassword = isValid == password?.contains("1234")
                        if (isValidPassword) {
                            if (email?.startsWith("guest".lowercase()) == true) {
                                onNavigateToHomeScreen()
                            } else {
                                onNavigateToOwnerHomeScreen()
                            }
                        }
                    }
                },
                text = stringResource(R.string.verify),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            // resend code
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.resend_code_to),
                    color = SubTextColor,
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Normal,
                )
                Text(
                    text = state.timeLeft.toCountdownTimer(),
                    color = SubTextColor,
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TravenorTheme {
        VerificationScreen(
            resultStore = ResultStore(),
            onNavigateToSignInScreen = {},
            onNavigateToHomeScreen = {},
            onNavigateToOwnerHomeScreen = {},
            focusRequesters = remember {
                (1..4).map { FocusRequester() }
            },
            state = VerificationState(),
            onAction = {}
        )
    }
}