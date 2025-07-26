package com.room.booking.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun RootLayout(
    topBarContent: @Composable () -> Unit,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    content: @Composable BoxScope.() -> Unit,
) {
    Scaffold(
        topBar = { topBarContent() },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            content()
        }
    }
}