package com.room.booking.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.room.booking.R
import com.room.booking.core.ui.RoomCard
import com.room.booking.core.ui.RootHeader
import com.room.booking.core.ui.RootLayout

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToRoomDetails: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RootLayout(
        topBarContent = {
            RootHeader(
                titleResId = R.string.rooms_title,
                menuContent = {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(
                                    if (state.isBookedRoomsShown) {
                                        R.string.hide_booked_label
                                    } else {
                                        R.string.show_booked_label
                                    }
                                ),
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        onClick = {
                            viewModel.changeBookedRoomsVisibility()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(R.string.reset_booking_label),
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        onClick = {
                            viewModel.changeBookedRoomsVisibility()
                        }
                    )
                }
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp)
        ) {
            items(
                items = state.filteredRooms,
                key = { it.id }
            ) { room ->
                RoomCard(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                    roomInfo = room,
                    onButtonClick = { navigateToRoomDetails(it) }
                )
            }
        }
    }
}
