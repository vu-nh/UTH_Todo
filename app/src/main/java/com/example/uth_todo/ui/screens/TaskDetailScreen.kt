package com.example.uth_todo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uth_todo.data.Task
import com.example.uth_todo.data.studyCategory
import com.example.uth_todo.ui.theme.BeVietnamProFontFamily

@Composable
fun TaskDetailScreen(
    task: Task = Task(
        id = 1,
        title = "Ôn chương 3 – Cấu trúc dữ liệu",
        category = studyCategory,
        time = "Hôm nay, 14:00",
        isCompleted = false
    ),
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onCompleteClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    // Trạng thái mẫu cho các việc nhỏ (sub-tasks) trong màn hình chi tiết
    var subTasks by remember {
        mutableStateOf(
            listOf(
                SubTask(id = 1, title = "Đọc lại slide bài giảng", isDone = true),
                SubTask(id = 2, title = "Làm 10 bài tập cuối chương...", isDone = false)
            )
        )
    }

    var newSubTaskTitle by remember { mutableStateOf("") }

    val completedSubTasksCount = subTasks.count { it.isDone }
    val totalSubTasks = subTasks.size
    val progressFraction = if (totalSubTasks > 0) completedSubTasksCount.toFloat() / totalSubTasks else 0f

    Scaffold(
        containerColor = Color(0xFFF7F8FC),
        bottomBar = {
            // Nút hành động cố định ở đáy
            Surface(
                color = Color(0xFFF7F8FC),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Nút Đánh dấu hoàn thành
                    Button(
                        onClick = onCompleteClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2F5BEA)
                        )
                    ) {
                        Text(
                            text = if (task.isCompleted) "Đã hoàn thành" else "Đánh dấu hoàn thành",
                            fontSize = 16.sp,
                            fontFamily = BeVietnamProFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Nút Xóa công việc
                    Text(
                        text = "Xóa công việc",
                        fontSize = 15.sp,
                        fontFamily = BeVietnamProFontFamily,
                        fontWeight = FontWeight.W600,
                        color = Color(0xFFDC2626),
                        modifier = Modifier
                            .clickable { onDeleteClick() }
                            .padding(4.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Top Bar: Quay lại Danh sách & Nút Sửa
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onBackClick() }
                ) {
                    Text(
                        text = "‹",
                        fontSize = 26.sp,
                        color = Color(0xFF2F5BEA),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Danh sách",
                        fontSize = 16.sp,
                        fontFamily = BeVietnamProFontFamily,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF2F5BEA)
                    )
                }

                Text(
                    text = "Sửa",
                    fontSize = 16.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W600,
                    color = Color(0xFF2F5BEA),
                    modifier = Modifier.clickable { onEditClick() }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Các thẻ phân loại (Category & Priority badges)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Badge danh mục
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFEEF2FF))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = task.category.name,
                        fontSize = 12.sp,
                        fontFamily = BeVietnamProFontFamily,
                        fontWeight = FontWeight.W600,
                        color = Color(0xFF526CE5)
                    )
                }

                // Badge ưu tiên
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF3F4F6))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Ưu tiên vừa",
                        fontSize = 12.sp,
                        fontFamily = BeVietnamProFontFamily,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF6B7280)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Tiêu đề công việc lớn
            Text(
                text = task.title,
                fontSize = 24.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2937),
                lineHeight = 32.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Khung thông tin thời gian & nhắc nhở
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "📅", fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Hạn chót",
                                fontSize = 14.sp,
                                fontFamily = BeVietnamProFontFamily,
                                color = Color(0xFF6B7280)
                            )
                        }
                        Text(
                            text = "Hôm nay, 14:00",
                            fontSize = 14.sp,
                            fontFamily = BeVietnamProFontFamily,
                            fontWeight = FontWeight.W600,
                            color = Color(0xFF1F2937)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "🔔", fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Nhắc nhở",
                                fontSize = 14.sp,
                                fontFamily = BeVietnamProFontFamily,
                                color = Color(0xFF6B7280)
                            )
                        }
                        Text(
                            text = "Trước 15 phút",
                            fontSize = 14.sp,
                            fontFamily = BeVietnamProFontFamily,
                            fontWeight = FontWeight.W600,
                            color = Color(0xFF1F2937)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Phần Ghi chú
            Text(
                text = "Ghi chú",
                fontSize = 15.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.W600,
                color = Color(0xFF1F2937)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Tập trung phần cây nhị phân và bảng băm.",
                    fontSize = 14.sp,
                    fontFamily = BeVietnamProFontFamily,
                    color = Color(0xFF4B5563),
                    lineHeight = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Phần Việc nhỏ (Sub-tasks) & Tiến độ
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Việc nhỏ",
                    fontSize = 15.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W600,
                    color = Color(0xFF1F2937)
                )

                Text(
                    text = "$completedSubTasksCount/$totalSubTasks",
                    fontSize = 14.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W600,
                    color = Color(0xFF6B7280)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Thanh tiến độ việc nhỏ
            LinearProgressIndicator(
                progress = { progressFraction },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = Color(0xFF2F5BEA),
                trackColor = Color(0xFFE5E7EB)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Danh sách các việc nhỏ dạng Card bo góc
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    subTasks.forEachIndexed { index, subTask ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    subTasks = subTasks.mapIndexed { i, s ->
                                        if (i == index) s.copy(isDone = !s.isDone) else s
                                    }
                                }
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Checkbox nhỏ
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(if (subTask.isDone) Color(0xFF2F5BEA) else Color.Transparent)
                                    .border(
                                        width = if (subTask.isDone) 0.dp else 1.5.dp,
                                        color = if (subTask.isDone) Color.Transparent else Color(0xFFD1D5DB),
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (subTask.isDone) {
                                    Text(text = "✓", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = subTask.title,
                                fontSize = 14.sp,
                                fontFamily = BeVietnamProFontFamily,
                                color = if (subTask.isDone) Color(0xFF9CA3AF) else Color(0xFF1F2937),
                                textDecoration = if (subTask.isDone) TextDecoration.LineThrough else TextDecoration.None,
                                modifier = Modifier.weight(1f)
                            )

                            // Nút Sửa / Xóa việc nhỏ
                            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                Text(text = "✏️", fontSize = 14.sp, modifier = Modifier.clickable { /* Sửa việc nhỏ */ })
                                Text(text = "🗑️", fontSize = 14.sp, modifier = Modifier.clickable {
                                    subTasks = subTasks.filterIndexed { i, _ -> i != index }
                                })
                            }
                        }

                        if (index < subTasks.size - 1) {
                            HorizontalDivider(color = Color(0xFFF3F4F6), thickness = 1.dp)
                        }
                    }

                    HorizontalDivider(color = Color(0xFFF3F4F6), thickness = 1.dp)

                    // Dòng thêm việc nhỏ mới
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "+",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2F5BEA),
                            modifier = Modifier.padding(horizontal = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Thêm việc nhỏ",
                            fontSize = 14.sp,
                            fontFamily = BeVietnamProFontFamily,
                            color = Color(0xFF9CA3AF),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Thêm",
                            fontSize = 14.sp,
                            fontFamily = BeVietnamProFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2F5BEA),
                            modifier = Modifier.clickable {
                                // Logic thêm việc nhỏ ở đây
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// Data class phụ trợ cho việc nhỏ
data class SubTask(
    val id: Int,
    val title: String,
    val isDone: Boolean
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TaskDetailScreenPreview() {
    TaskDetailScreen()
}