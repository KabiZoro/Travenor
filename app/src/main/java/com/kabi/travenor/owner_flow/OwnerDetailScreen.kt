@file:OptIn(ExperimentalMaterial3Api::class)

package com.kabi.travenor.owner_flow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kabi.travenor.R
import com.kabi.travenor.app.db.BookingViewModel
import com.kabi.travenor.app.navigation.ResultStore
import com.kabi.travenor.core.presentation.design_system.theme.GrayColor
import com.kabi.travenor.core.presentation.design_system.theme.GreenColor
import com.kabi.travenor.core.presentation.design_system.theme.Primary
import com.kabi.travenor.core.presentation.design_system.theme.SfUiDisplay
import com.kabi.travenor.core.presentation.design_system.theme.SubTextColor
import com.kabi.travenor.core.presentation.design_system.theme.TextColor
import com.kabi.travenor.user_flow.main_screen.models.Place

@Composable
fun OwnerDetailScreen(
    viewModel: BookingViewModel,
    resultStore: ResultStore,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.listenForIncomingRequests()
    }
    val place = resultStore.getResult<Place>("place")
    var onConfirm by remember { mutableStateOf<Boolean?>(null) }
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(9.dp))
                        Text(
                            text = place?.name ?: "Title",
                            fontFamily = SfUiDisplay,
                            color = TextColor,
                            fontSize = 18.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Active Now",
                            fontFamily = SfUiDisplay,
                            color = GreenColor,
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.height(21.dp))
                        HorizontalDivider(
                            color = GrayColor,
                            thickness = 1.5.dp
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

        viewModel.activeRequestForDialog?.let { request ->
            AlertDialog(
                onDismissRequest = {
                    viewModel.activeRequestForDialog = null
                },
                title = {
                    Text(
                        text = "New Request from User",
                        fontFamily = SfUiDisplay,
                        color = SubTextColor,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                },
                text = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconButton(
                            onClick = {
                                onConfirm = true
                                viewModel.updateRequestStatus(request.id, "ACCEPTED")
                                viewModel.activeRequestForDialog = null
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Primary,
                                contentColor = SubTextColor
                            ),
                            modifier = Modifier
                                .size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                onConfirm = false
                                viewModel.updateRequestStatus(request.id, "REJECTED")
                                viewModel.activeRequestForDialog = null
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Primary,
                                contentColor = Color.Red
                            ),
                            modifier = Modifier
                                .size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp)
                            )
                        }
                    }
                },
                confirmButton = {},
                dismissButton = {},
                containerColor = GrayColor,
                properties = DialogProperties(
                    dismissOnClickOutside = true,
                    dismissOnBackPress = true
                )
                /*modifier = Modifier
                    .size(width = 269.dp, height = 160.dp),
                shape = RoundedCornerShape(16.dp)*/
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (onConfirm) {
                true -> {
                    Column(
                        modifier = Modifier
                            .size(width = 269.dp, height = 160.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Accepted Successfully",
                            fontFamily = SfUiDisplay,
                            color = SubTextColor,
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        IconButton(
                            onClick = {},
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Primary,
                                contentColor = SubTextColor
                            ),
                            modifier = Modifier
                                .size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp)
                            )
                        }
                    }
                }

                false -> {
                    Text(
                        text = "Request Rejected",
                        fontFamily = SfUiDisplay,
                        color = SubTextColor,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

                null -> {
                    Text("Waiting for requests...", color = SubTextColor)
                }

            }
        }

    }
}

@Preview
@Composable
private fun OwnerDetailScreenPreview() {
    OwnerDetailScreen(
        resultStore = ResultStore(),
        viewModel = viewModel<BookingViewModel>(),
    )
}