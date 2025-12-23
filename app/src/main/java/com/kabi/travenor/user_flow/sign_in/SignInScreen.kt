@file:OptIn(ExperimentalMaterial3Api::class)

package com.kabi.travenor.user_flow.sign_in

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.kabi.travenor.R
import com.kabi.travenor.app.navigation.ResultStore
import com.kabi.travenor.core.presentation.design_system.button.PrimaryButton
import com.kabi.travenor.core.presentation.design_system.theme.GrayColor
import com.kabi.travenor.core.presentation.design_system.theme.SfUiDisplay
import com.kabi.travenor.core.presentation.design_system.theme.SubTextColor
import com.kabi.travenor.core.presentation.design_system.theme.TextColor
import com.kabi.travenor.user_flow.sign_in.use_cases.validateEmail

@Composable
fun SignInScreen(
    resultStore: ResultStore,
    onNavigateToOnBoardScreen: () -> Unit,
    onNavigateToVerificationScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var isNotValidEmail by remember {
        mutableStateOf(false)
    }
    val auth = Firebase.auth
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateToOnBoardScreen,
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
                text = stringResource(R.string.sign_in_now),
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
            Text(
                text = stringResource(R.string.please_sign_in_to_continue_our_app),
                fontFamily = SfUiDisplay,
                color = SubTextColor,
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            )
            Spacer(
                modifier = Modifier
                    .height(58.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(14.dp),
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(
                        text = "www.uihut@gmail.com",
                        fontFamily = SfUiDisplay,
                        fontSize = 18.sp,
                        lineHeight = 20.sp,
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = GrayColor,
                    focusedBorderColor = GrayColor,
                    focusedTextColor = Color.Black,
                    focusedPlaceholderColor = Color.Black.copy(0.4f),
                    unfocusedPlaceholderColor = Color.Black.copy(0.4f),
                    cursorColor = Color.Black
                )
            )
            if (isNotValidEmail && validateEmail(email).errorMessage != null){
                Text(
                    text = validateEmail(email).errorMessage!!,
                    fontFamily = SfUiDisplay,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .align(Alignment.Start)
                )
            }
            Spacer(
                modifier = Modifier
                    .height(40.dp)
            )
            PrimaryButton(
                onClick = {
                    if(validateEmail(email).successful) {
                        password = "password@1234"
                        auth.signInWithEmailAndPassword(email, password)
                        resultStore.setResult("email", email)
                        resultStore.setResult("password", password)
                        onNavigateToVerificationScreen()
                    } else{
                        isNotValidEmail = true
                    }
                },
                text = stringResource(R.string.sign_in),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
    }
}