package com.kabi.travenor.owner_flow.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabi.travenor.R
import com.kabi.travenor.core.presentation.design_system.theme.Location_Pin
import com.kabi.travenor.core.presentation.design_system.theme.SubTextColor
import com.kabi.travenor.core.presentation.design_system.theme.TextColor
import com.kabi.travenor.user_flow.main_screen.models.Place

@Composable
fun OwnerPlaceCard(
    onCardClick:() -> Unit,
    place: Place,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .wrapContentSize()
            .clickable{ onCardClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                modifier = Modifier
                    .width(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(place.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 137.dp, height = 124.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = place.name,
                color = TextColor,
                fontSize = 18.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Filled.Location_Pin,
                    contentDescription = null,
                    tint = SubTextColor,
                    modifier = Modifier
                        .size(14.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = "${place.location}, India",
                    color = SubTextColor,
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                )
            }
            val text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                    )
                ) {
                    append("$894")
                }
                withStyle(
                    style = SpanStyle(
                        color = SubTextColor,
                    )
                ) {
                    append("/Person")
                }
            }
            Text(
                text = text,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 4.dp)
            )
        }
    }

}

@Preview
@Composable
private fun OwnerPlaceCardPreview() {
    OwnerPlaceCard(
        onCardClick = {},
        place = Place(
            image = R.drawable.delhi_image,
            name = "Kolkata Reservoir",
            description = "",
            location = "Kolkata"
        )
    )
}