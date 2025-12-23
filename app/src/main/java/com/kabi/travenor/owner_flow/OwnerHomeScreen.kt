@file:OptIn(ExperimentalMaterial3Api::class)

package com.kabi.travenor.owner_flow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MicNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.kabi.travenor.R
import com.kabi.travenor.app.navigation.ResultStore
import com.kabi.travenor.core.presentation.design_system.theme.GrayColor
import com.kabi.travenor.core.presentation.design_system.theme.Microphone_Icon
import com.kabi.travenor.core.presentation.design_system.theme.Search_Icon
import com.kabi.travenor.core.presentation.design_system.theme.SfUiDisplay
import com.kabi.travenor.core.presentation.design_system.theme.SubTextColor
import com.kabi.travenor.core.presentation.design_system.theme.TextColor
import com.kabi.travenor.owner_flow.components.OwnerPlaceCard
import com.kabi.travenor.user_flow.main_screen.models.Place
import com.kabi.travenor.user_flow.main_screen.places

@Composable
fun OwnerHomeScreen(
    resultStore: ResultStore,
    onNavigateToOwnerDetailScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentUid = remember { Firebase.auth.currentUser?.uid ?: "" }
    val ownerPlaces = remember(currentUid) {
        listOf(
            Place(
                image = R.drawable.kolkata_image,
                name = "Kolkata Reservoir",
                ownerId = currentUid,
                description = "",
                location = "Kolkata"
            ),
            Place(
                image = R.drawable.bombay_image,
                name = "Bombay Tirtugas",
                ownerId = currentUid,
                description = "",
                location = "Bombay"
            ),
            Place(
                image = R.drawable.chennai_image,
                name = "Chennai Resort",
                ownerId = currentUid,
                description = "",
                location = "Chennai"
            ),
            Place(
                image = R.drawable.delhi_image,
                name = "Delhi Hills",
                ownerId = currentUid,
                description = "",
                location = "Delhi"
            )
        )
    }
    LaunchedEffect(Unit) {
        println("DEBUG_OWNER_UID: $currentUid")
    }
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Search",
                        fontFamily = SfUiDisplay,
                        color = TextColor,
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                    )
                },
                actions = {
                    TextButton(
                        onClick = {}
                    ) {
                        Text(
                            text = "Cancel",
                            fontFamily = SfUiDisplay,
                            color = Color.Blue,
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )
        },
        contentWindowInsets = WindowInsets.safeContent,
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            // search container
            var text by remember {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                shape = RoundedCornerShape(16.dp),
                textStyle = TextStyle(
                    fontFamily = SfUiDisplay,
                    color = TextColor,
                    fontSize = 20.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                placeholder = {
                    Text(
                        text = "Search Places",
                        fontFamily = SfUiDisplay,
                        color = SubTextColor,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search_Icon,
                        contentDescription = null,
                        tint = SubTextColor,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .padding(start = 16.dp)
                            .size(17.dp)
                    )
                },
                trailingIcon = {
                    Row(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .padding(vertical = 12.dp)
                            .padding(end = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                    ) {
                        VerticalDivider(
                            color = SubTextColor,
                            thickness = 1.5.dp
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Default.Microphone_Icon,
                            contentDescription = null,
                            tint = SubTextColor,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = GrayColor,
                    unfocusedContainerColor = GrayColor,
                    focusedBorderColor = GrayColor,
                    unfocusedBorderColor = GrayColor,
                    cursorColor = TextColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding()
            )
            // search places
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Search Places",
                fontFamily = SfUiDisplay,
                color = TextColor,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
            )
            // lazy grid vertical places
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(ownerPlaces.size) { place ->
                    OwnerPlaceCard(
                        place = ownerPlaces[place],
                        onCardClick = {
                            resultStore.setResult("place", ownerPlaces[place])
                            onNavigateToOwnerDetailScreen()
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun OwnerHomeScreenPreview() {
    OwnerHomeScreen(
        resultStore = ResultStore(),
        onNavigateToOwnerDetailScreen = {}
    )
}