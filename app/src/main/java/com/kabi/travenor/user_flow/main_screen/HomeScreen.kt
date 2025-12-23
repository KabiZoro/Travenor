@file:OptIn(ExperimentalMaterial3Api::class)

package com.kabi.travenor.user_flow.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabi.travenor.R
import com.kabi.travenor.app.navigation.ResultStore
import com.kabi.travenor.core.presentation.design_system.theme.ActionColor
import com.kabi.travenor.core.presentation.design_system.theme.Arc_Vector
import com.kabi.travenor.core.presentation.design_system.theme.GrayColor
import com.kabi.travenor.core.presentation.design_system.theme.SfUiDisplay
import com.kabi.travenor.core.presentation.design_system.theme.TextColor
import com.kabi.travenor.user_flow.main_screen.components.PlaceCard
import com.kabi.travenor.user_flow.main_screen.models.Place

@Composable
fun HomeScreen(
    resultStore: ResultStore,
    onNavigateToDetailScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {},
        contentWindowInsets = WindowInsets.safeContent,
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(start = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(GrayColor),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.face_4),
                        contentDescription = null,
                        modifier = Modifier
                            .size(37.dp)
                            .padding(vertical = 4.dp)
                            .padding(start = 4.dp)
                    )
                    Text(
                        text = stringResource(R.string.username),
                        color = TextColor,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .padding(start = 6.dp, end = 13.dp)
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )
            val text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = TextColor,
                        fontFamily = SfUiDisplay,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Light
                    )
                ) {
                    append("Explore the\n")
                }
                withStyle(
                    style = SpanStyle(
                        color = TextColor,
                        fontFamily = SfUiDisplay,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Beautiful ")
                }
                withStyle(
                    style = SpanStyle(
                        color = ActionColor,
                        fontFamily = SfUiDisplay,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("world!")
                }
            }
            Text(
                text = text,
                lineHeight = 50.sp,
                textAlign = TextAlign.Start
            )
            Image(
                imageVector = Icons.Filled.Arc_Vector,
                contentDescription = null,
                modifier = Modifier
                    .size(width = 365.dp, height = 15.dp)
                    .offset(
                        x = 0.dp,
                        y = 0.dp
                    )
            )
            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            Text(
                text = stringResource(R.string.best_destination),
                color = TextColor,
                fontFamily = SfUiDisplay,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(places.size) {
                    PlaceCard(
                        place = places[it],
                        modifier = Modifier
                            .clickable {
                                resultStore.setResult("place", places[it])
                                onNavigateToDetailScreen()
                            }
                    )
                    Spacer(
                        modifier = Modifier
                            .width(16.dp)
                    )
                }
            }
        }
    }
}

val places = listOf(
    Place(
        image = R.drawable.kolkata_reservoir,
        name = "Kolkata Reservoir",
        description = "You will get a complete travel package on the beaches. Packages in the form of airline tickets, recommended Hotel rooms, Transportation, Have you ever been on holiday to the Greek ETC...",
        location = "Kolkata"
    ),
    Place(
        image = R.drawable.bombay_tirtugas,
        name = "Bombay Tirtugas",
        description = "You will get a complete travel package on the beaches. Packages in the form of airline tickets, recommended Hotel rooms, Transportation, Have you ever been on holiday to the Greek ETC...",
        location = "Bombay"
    )
)

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        resultStore = ResultStore(),
        onNavigateToDetailScreen = {}
    )
}