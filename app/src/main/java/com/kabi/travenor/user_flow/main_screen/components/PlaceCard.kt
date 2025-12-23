package com.kabi.travenor.user_flow.main_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabi.travenor.R
import com.kabi.travenor.core.presentation.design_system.theme.Bookmark
import com.kabi.travenor.core.presentation.design_system.theme.GrayColor
import com.kabi.travenor.core.presentation.design_system.theme.Location_Pin
import com.kabi.travenor.core.presentation.design_system.theme.StarColor
import com.kabi.travenor.core.presentation.design_system.theme.SubTextColor
import com.kabi.travenor.core.presentation.design_system.theme.TextColor
import com.kabi.travenor.user_flow.main_screen.models.Place

@Composable
fun PlaceCard(
    place: Place,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max),
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
                .fillMaxSize()
                .padding(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .height(IntrinsicSize.Max)
            ) {
                Image(
                    painter = painterResource(place.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 240.dp, height = 286.dp)
                        .clip(RoundedCornerShape(24.dp))
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    IconButton(
                        onClick = {},
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = TextColor.copy(0.3f),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(14.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Bookmark,
                            contentDescription = null
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .padding(top = 14.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = place.name,
                    color = TextColor,
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(28.dp))
                Icon(
                    imageVector = Icons.Filled.StarRate,
                    contentDescription = null,
                    tint = StarColor,
                    modifier = Modifier
                        .size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "4.7",
                    color = TextColor,
                    fontSize = 15.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Filled.Location_Pin,
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${place.location}, India",
                    color = SubTextColor,
                    fontSize = 15.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.width(36.dp))
                OverlapProfiles(
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }

}

@Preview
@Composable
private fun PlaceCardPreview() {
    PlaceCard(
        place = Place(
            image = R.drawable.kolkata_reservoir,
            name = "Kolkata Reservoir",
            description = "",
            location = "Kolkata"
        )
    )
}