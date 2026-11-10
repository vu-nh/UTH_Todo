package com.example.uth_todo.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.uth_todo.ui.theme.BeVietnamProFontFamily
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


// Tự động lấy ngày hiện tại theo múi giờ Việt Nam
private fun getCurrentDateText(): String {
    val formatter = SimpleDateFormat(
        "EEEE, dd 'tháng' M",
        Locale("vi", "VN")
    ).apply {
        timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
    }

    val dateText = formatter.format(Date())

    return dateText.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale("vi", "VN")) else it.toString()
    }
}

@Composable
fun HomeHeader(
    dateText: String = getCurrentDateText(),
    userInitial: String = "K",
    onNotificationClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(
                    Color(0xFFE6EDFF),
                    shape = RoundedCornerShape(24.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = userInitial,
                color = Color(0xFF1F4FD1),
                fontSize = 17.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.W700
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Ngày tháng và lời chào
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = dateText,
                color = Color(0xFF6B6B70),
                fontSize = 14.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.W400
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Chào bạn!",
                color = Color(0xFF1C1C1E),
                fontSize = 24.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.W700
            )
        }

        // Nút thông báo
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(
                    Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(24.dp)
                )
                .clickable {
                    onNotificationClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = com.example.uth_todo.R.drawable.thong_bao),
                contentDescription = "Thông báo",
                modifier = Modifier.size(22.dp)
            )

            // Chấm đỏ thông báo
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 10.dp, end = 10.dp)
                    .size(11.2.dp)
                    .border(width = 1.6.dp, Color(0xFFFFFFFF), RoundedCornerShape(4.dp))
                    .background(Color(0xFFE5484D),
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}
