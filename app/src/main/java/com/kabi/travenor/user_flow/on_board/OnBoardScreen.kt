package com.kabi.travenor.user_flow.on_board

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabi.travenor.R
import com.kabi.travenor.core.presentation.design_system.button.PrimaryButton
import com.kabi.travenor.core.presentation.design_system.theme.ActionColor
import com.kabi.travenor.core.presentation.design_system.theme.Arc_Vector
import com.kabi.travenor.core.presentation.design_system.theme.Geometric415
import com.kabi.travenor.core.presentation.design_system.theme.GillSansMt
import com.kabi.travenor.core.presentation.design_system.theme.SubTextColor

@Composable
fun OnBoardScreen(
    onNavigateToSignInScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Color.White,
        contentWindowInsets = WindowInsets.statusBars
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.on_board),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(460.dp)
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .height(40.dp)
            )
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 33.dp
                    )
            ) {
                val text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black
                        )
                    ) { append(stringResource(R.string.life_is_short_and_the_world_is)) }
                    withStyle(
                        style = SpanStyle(
                            color = ActionColor
                        )
                    ) { append(stringResource(R.string.wide)) }
                }
                Text(
                    text = text,
                    fontFamily = Geometric415,
                    fontSize = 44.sp,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                )
                Image(
                    imageVector = Icons.Filled.Arc_Vector,
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 662.dp, height = 15.dp)
                        .offset(
                            x = 70.dp,
                            y = 0.dp
                        )
                )
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                )
                Text(
                    text = "At Friends tours and travel, we customize reliable and trustworthy educational tours to destinations all over the world",
                    fontFamily = GillSansMt,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    color = SubTextColor,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(
                modifier = Modifier
                    .height(100.dp)
            )
            PrimaryButton(
                onClick = onNavigateToSignInScreen,
                text = "Get Started",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 40.dp)
            )
        }
    }
}