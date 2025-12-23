package com.kabi.travenor.core.presentation.design_system.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kabi.travenor.R

val Geometric415
    @Composable
    get() = FontFamily(
        Font(R.font.geometr415_blk_bt_black, FontWeight.Black)
    )

val GillSansMt
    @Composable
    get() = FontFamily(
        Font(R.font.gill_sans_mt_regular, FontWeight.Normal)
    )

val SfUiDisplay
    @Composable
    get() = FontFamily(
        Font(R.font.sf_ui_display_regular, FontWeight.Normal)
    )


// Set of Material typography styles to start with
val Typography
    @Composable
    get() = Typography(
        bodyLarge = TextStyle(
            fontFamily = SfUiDisplay,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(
            fontFamily = GillSansMt,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = Geometric415,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )