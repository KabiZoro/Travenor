@file:OptIn(ExperimentalMaterial3Api::class)

package com.kabi.travenor.user_flow.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kabi.travenor.R
import com.kabi.travenor.app.db.BookingViewModel
import com.kabi.travenor.app.navigation.ResultStore
import com.kabi.travenor.core.presentation.design_system.button.PrimaryButton
import com.kabi.travenor.core.presentation.design_system.theme.ActionColor
import com.kabi.travenor.core.presentation.design_system.theme.GrayColor
import com.kabi.travenor.core.presentation.design_system.theme.Location_Pin
import com.kabi.travenor.core.presentation.design_system.theme.SfUiDisplay
import com.kabi.travenor.core.presentation.design_system.theme.StarColor
import com.kabi.travenor.core.presentation.design_system.theme.SubTextColor
import com.kabi.travenor.core.presentation.design_system.theme.TextColor
import com.kabi.travenor.user_flow.main_screen.models.Place

@Composable
fun DetailScreen(
    viewModel: BookingViewModel,
    resultStore: ResultStore,
    onNavigateToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val place = resultStore.getResult<Place>("place")
    val currentPlaceStatus = viewModel.bookingStatus[place?.name ?: ""] ?: "IDLE"
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                title = {
                    Text(
                        text = "Details",
                        color = Color.White,
                        fontFamily = SfUiDisplay,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateToHomeScreen,
                        modifier = Modifier
                            .clip(RoundedCornerShape(44.dp))
                            .background(TextColor.copy(alpha = 0.3f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChevronLeft,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.White,
        contentWindowInsets = WindowInsets.statusBars
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(place?.image ?: R.drawable.kolkata_reservoir),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .offset(x = 0.dp, y = (-30).dp)
                    .height(550.dp)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(461.dp)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        // title
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier
                            ) {
                                Text(
                                    text = place?.name ?: "Title",
                                    color = TextColor,
                                    fontFamily = SfUiDisplay,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "${place?.location ?: "Location"}, India",
                                    color = SubTextColor,
                                    fontFamily = SfUiDisplay,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                            Image(
                                painter = painterResource(R.drawable.face_2),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(48.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        // info
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            // location
                            Icon(
                                imageVector = Icons.Filled.Location_Pin,
                                contentDescription = null,
                                tint = SubTextColor,
                                modifier = Modifier
                                    .size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = place?.location ?: "Location",
                                color = SubTextColor,
                                fontFamily = SfUiDisplay,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.width(54.dp))
                            // rating
                            Icon(
                                imageVector = Icons.Filled.StarRate,
                                contentDescription = null,
                                tint = StarColor,
                                modifier = Modifier
                                    .size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "4.7",
                                color = TextColor,
                                fontSize = 15.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "(2498)",
                                color = SubTextColor,
                                fontSize = 15.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .weight(1f)
                            )
                            // price
                            val text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Blue,
                                    )
                                ) {
                                    append("$59")
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
                                fontSize = 15.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        // photos
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            items(photos) { photo ->
                                Image(
                                    painter = painterResource(photo),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(42.dp)
                                )
                                Spacer(modifier = Modifier.width(26.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        // about
                        Text(
                            text = "About Destination",
                            fontFamily = SfUiDisplay,
                            color = TextColor,
                            fontSize = 20.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                        )
                        val text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = SubTextColor,
                                )
                            ) {
                                append(place?.description ?: "Description")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = ActionColor,
                                )
                            ) {
                                append(" Read More")
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = text,
                            fontFamily = SfUiDisplay,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        // button
                        PrimaryButton(
                            onClick = {
                                if (currentPlaceStatus == "IDLE") {
                                    place?.ownerId?.let { id ->
                                        viewModel.createBooking(id, place.name)
                                    }
                                    /*val ownerId = place?.ownerId
                                    viewModel.createBooking(
                                        ownerId ?: "OWNER_1234",
                                        place?.name ?: ""
                                    )*/
                                }
                            },
                            text = when (currentPlaceStatus) {
                                "PENDING" -> "Requesting..."
                                "ACCEPTED" -> "Accepted"
                                else -> "Book Now"
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }

}

val photos = listOf(
    R.drawable.photo_1,
    R.drawable.photo_2,
    R.drawable.photo_3,
    R.drawable.photo_4,
    R.drawable.photo_5
)

@Preview
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        resultStore = ResultStore(),
        viewModel = viewModel<BookingViewModel>(),
        onNavigateToHomeScreen = {}
    )
}