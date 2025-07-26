package com.room.booking.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route(val routeName: String) {
    data object HomeRoute : Route("home")
    data object DetailsRoute : Route("details/{roomId}") {
        fun createRoute(roomId: String) = "details/$roomId"
    }
}