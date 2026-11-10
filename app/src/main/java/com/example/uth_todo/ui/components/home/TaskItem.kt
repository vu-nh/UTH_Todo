package com.example.uth_todo.ui.components.home

import androidx.compose.foundation.border
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.uth_todo.data.Task
import com.example.uth_todo.data.lifeCategory
import com.example.uth_todo.data.studyCategory
import com.example.uth_todo.ui.theme.BeVietnamProFontFamily

@Composable
fun TaskItem(
    task: Task,
    onToggle: () -> Unit = {},
    onItemClick: () -> Unit = {} // Thêm sự kiện bấm vào cả dòng để xem chi tiết
) {
    val isStudy = task.category.name == "Học tập"

    val categoryColor = if (isStudy) Color(0xFFEEF2FF) else Color(0xFFFFF4E5)
    val categoryTextColor = if (isStudy) Color(0xFF526CE5) else Color(0xFFE69A36)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { onItemClick() } // Bấm vào toàn bộ ô để xem chi tiết
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox chuẩn Figma
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(
                    if (task.isCompleted) Color(0xFF2F5BEA) else Color.Transparent
                )
                .border(
                    width = if (task.isCompleted) 0.dp else 1.5.dp,
                    color = if (task.isCompleted) Color.Transparent else Color(0xFFD1D5DB),
                    shape = CircleShape
                )
                .clickable { onToggle() },
            contentAlignment = Alignment.Center
        ) {
            if (task.isCompleted) {
                Text(
                    text = "✓",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Nội dung Công việc
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = task.title,
                fontSize = 14.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.Medium,
                color = if (task.isCompleted) Color(0xFF9CA3AF) else Color(0xFF1F2937),
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Chip Danh mục
                Text(
                    text = task.category.name,
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(categoryColor)
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    color = categoryTextColor,
                    fontSize = 11.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W500
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Giờ thực hiện từ API
                Text(
                    text = task.time,
                    color = Color(0xFF9CA3AF),
                    fontSize = 11.sp,
                    fontFamily = BeVietnamProFontFamily
                )
            }
        }

        // Mũi tên mở chi tiết công việc chuẩn UI Figma
        Text(
            text = "›",
            fontSize = 20.sp,
            color = Color(0xFF9CA3AF),
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F6FA))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TaskItem(
            task = Task(
                id = 1,
                title = "Tập thể dục 30 phút",
                category = lifeCategory,
                time = "06:30",
                isCompleted = true
            )
        )

        TaskItem(
            task = Task(
                id = 2,
                title = "Ôn chương 3 – Cấu trúc dữ liệu",
                category = studyCategory,
                time = "14:00",
                isCompleted = false
            )
        )
    }
}