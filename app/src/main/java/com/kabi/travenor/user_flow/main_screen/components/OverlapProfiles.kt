package com.kabi.travenor.user_flow.main_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.kabi.travenor.R
import com.kabi.travenor.core.presentation.design_system.theme.ShapeColor

@Composable
fun OverlapProfiles(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .wrapContentSize(),
    ) {
        val images = listOf(
            R.drawable.face_1,
            R.drawable.face_3,
            R.drawable.face_2
        )
        images.forEachIndexed { index, image ->
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .offset(x = (index * 12).dp)
                    .clip(CircleShape)
                    .zIndex(index.toFloat()),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .size(24.dp)
                .offset(x = (images.size * 12).dp)
                .clip(CircleShape)
                .background(ShapeColor)
                .zIndex((images.size + 1).toFloat()),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+50",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun OverlapProfilesPreview() {
    OverlapProfiles()
}