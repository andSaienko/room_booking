@file:RequiresApi(Build.VERSION_CODES.O)

package com.room.booking.presentation.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.room.booking.R
import com.room.booking.core.ui.RootHeader
import com.room.booking.core.ui.RootLayout
import com.room.booking.core.ui.theme.PrimaryColor

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isShowSnackBar) {
        if (state.isShowSnackBar) snackBarHostState.showSnackbar("Room is booked")
    }

    RootLayout(
        topBarContent = {
            RootHeader(
                titleResId = R.string.details_title,
                isBackButtonVisible = true,
                onBackClick = { navigateBack() },
            )
        },
        snackBarHostState = snackBarHostState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {
            state.room?.let {
                Column {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Text(
                        text = stringResource(R.string.capacity_label, it.capacity),
                        style = MaterialTheme.typography.labelSmall,
                    )
                    Text(
                        text = stringResource(
                            R.string.available_time_label, it.freeFrom, it.freeUntil
                        ),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                if (state.isBookButtonEnabled) {
                    BookButton {
                        viewModel.bookRoom()
                    }
                }
            }
        }
    }
}

@Composable
private fun BookButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(PrimaryColor)
            .height(62.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.book_button),
            style = MaterialTheme.typography.labelSmall,
        )
    }
}