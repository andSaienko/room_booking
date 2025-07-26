package com.room.booking.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.room.booking.presentation.details.DetailsScreen
import com.room.booking.presentation.home.HomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Route.HomeRoute.routeName
    ) {
        composable(Route.HomeRoute.routeName) {
            HomeScreen(
                navigateToRoomDetails = { roomId ->
                    navController.navigate(Route.DetailsRoute.createRoute(roomId))
                }
            )
        }
        composable(Route.DetailsRoute.routeName) {
            DetailsScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}