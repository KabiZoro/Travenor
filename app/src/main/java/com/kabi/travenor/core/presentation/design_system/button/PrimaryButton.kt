package com.kabi.travenor.core.presentation.design_system.button

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabi.travenor.core.presentation.design_system.theme.GillSansMt
import com.kabi.travenor.core.presentation.design_system.theme.Primary
import com.kabi.travenor.core.presentation.design_system.theme.SfUiDisplay
import com.kabi.travenor.core.presentation.design_system.theme.SubTextColor

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(IntrinsicSize.Max),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(18.dp)
    ) {
        Text(
            text = text,
            fontFamily = SfUiDisplay,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}