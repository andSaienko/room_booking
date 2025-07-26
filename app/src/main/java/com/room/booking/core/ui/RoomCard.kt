package com.room.booking.core.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.room.booking.R
import com.room.booking.core.ui.theme.ButtonDisabled
import com.room.booking.core.ui.theme.PrimaryColor
import com.room.booking.domain.model.MeetingRoom
import java.time.LocalTime

@Composable
fun RoomCard(
    modifier: Modifier = Modifier,
    roomInfo: MeetingRoom,
    onButtonClick: (String) -> Unit = {},
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.padding(vertical = 12.dp)) {
                Text(
                    text = roomInfo.name,
                    style = MaterialTheme.typography.labelLarge,
                )
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = stringResource(R.string.capacity_label, roomInfo.capacity),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (roomInfo.isBooked) ButtonDisabled else PrimaryColor)
                    .height(42.dp)
                    .width(104.dp)
                    .then(
                        if (roomInfo.isBooked.not()) Modifier.clickable { onButtonClick(roomInfo.id) } else Modifier
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(if (roomInfo.isBooked) R.string.booked_label else R.string.details_label),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun RoomCardPreview() {
    RoomCard(
        roomInfo = MeetingRoom(
            id = "1",
            name = "Meeting Room A",
            capacity = 10,
            isBooked = false,
            freeFrom = LocalTime.of(9, 0),
            freeUntil = LocalTime.of(18, 0)
        )
    ) {}
}