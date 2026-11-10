package com.example.uth_todo.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uth_todo.ui.theme.BeVietnamProFontFamily

@Composable
fun CategoryCard(
    categoryName: String,
    remainingTasks: Int,
    iconPainter: Painter,
    backgroundColor: Color,
    iconBackgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(14.dp)
    ) {
        // Khung chứa Icon hình ảnh
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(iconBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = iconPainter,
                contentDescription = categoryName,
                modifier = Modifier.size(20.dp) // Kích thước icon bên trong
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Tên danh mục
        Text(
            text = categoryName,
            fontSize = 15.sp,
            fontFamily = BeVietnamProFontFamily,
            fontWeight = FontWeight.W600,
            color = Color(0xFF1C1C1E)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Số công việc còn lại
        Text(
            text = "$remainingTasks việc còn lại",
            fontSize = 13.sp,
            fontFamily = BeVietnamProFontFamily,
            fontWeight = FontWeight.W400,
            color = Color(0xFF6B6B70)
        )
    }
}