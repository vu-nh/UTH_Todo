package com.example.uth_todo.ui.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uth_todo.data.Task
import com.example.uth_todo.data.TaskPriority
import com.example.uth_todo.data.lifeCategory
import com.example.uth_todo.data.studyCategory
import com.example.uth_todo.ui.components.home.BottomNavigationBar
import com.example.uth_todo.ui.theme.BeVietnamProFontFamily

@Composable
fun TaskListScreen(
    tasks: List<Task> = sampleTasks,
    onNavigateToHome: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onTaskClick: (Task) -> Unit = {},
    onToggleTask: (Task) -> Unit = {}
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryFilter by remember { mutableStateOf("Tất cả") }

    // Lọc công việc theo ô tìm kiếm & chip danh mục
    val filteredTasks = tasks.filter { task ->
        val matchesSearch = task.title.contains(searchQuery, ignoreCase = true)
        val matchesCategory = when (selectedCategoryFilter) {
            "Học tập" -> task.category.id == studyCategory.id || task.category.name == "Học tập"
            "Cuộc sống" -> task.category.id == lifeCategory.id || task.category.name == "Cuộc sống"
            else -> true
        }
        matchesSearch && matchesCategory
    }

    val pendingTasks = filteredTasks.filter { !it.isCompleted }
    val completedTasks = filteredTasks.filter { it.isCompleted }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = "Danh sách",
                onItemSelected = { item ->
                    if (item == "Trang chủ") onNavigateToHome()
                    if (item == "Cài đặt") onNavigateToProfile()
                },
                onAddClick = {
                    Toast.makeText(context, "Thêm công việc mới", Toast.LENGTH_SHORT).show()
                }
            )
        },
        containerColor = Color(0xFFF7F8FC)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Header: Danh sách & Số việc chưa xong
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Danh sách",
                    fontSize = 28.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W700, // 28 / normal · 700
                    color = Color(0xFF1F2937)
                )

                Text(
                    text = "${pendingTasks.size} việc chưa xong",
                    fontSize = 14.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W400, // 14 / normal · 400
                    color = Color(0xFF6B7280)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Thanh tìm kiếm
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFEFEFF4)),
                placeholder = {
                    Text(
                        text = "Tìm công việc",
                        fontSize = 15.sp,
                        fontFamily = BeVietnamProFontFamily,
                        color = Color(0xFF8E8E93)
                    )
                },
                leadingIcon = {
                    Text(text = "🔍", fontSize = 14.sp)
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Bộ lọc danh mục (Tất cả, Học tập, Cuộc sống)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Tất cả", "Học tập", "Cuộc sống").forEach { category ->
                    val isSelected = selectedCategoryFilter == category
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) Color(0xFF1C1C1E) else Color.White)
                            .border(
                                width = if (isSelected) 0.dp else 1.dp,
                                color = Color(0xFFE5E7EB),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable { selectedCategoryFilter = category }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = category,
                            fontSize = 14.sp,
                            fontFamily = BeVietnamProFontFamily,
                            fontWeight = FontWeight.W600, // 14 / normal · 600
                            color = if (isSelected) Color.White else Color(0xFF1F2937)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Nhóm CẦN LÀM
            if (pendingTasks.isNotEmpty()) {
                Text(
                    text = "CẦN LÀM",
                    fontSize = 13.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W600, // 13 / normal · 600
                    color = Color(0xFF6B7280)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    pendingTasks.forEach { task ->
                        TaskListCard(
                            task = task,
                            onToggle = { onToggleTask(task) },
                            onClick = { onTaskClick(task) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // Nhóm ĐÃ XONG
            if (completedTasks.isNotEmpty()) {
                Text(
                    text = "ĐÃ XONG",
                    fontSize = 13.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W600, // 13 / normal · 600
                    color = Color(0xFF6B7280)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    completedTasks.forEach { task ->
                        TaskListCard(
                            task = task,
                            onToggle = { onToggleTask(task) },
                            onClick = { onTaskClick(task) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TaskListCard(
    task: Task,
    onToggle: () -> Unit,
    onClick: () -> Unit
) {
    val isStudy = task.category.name == "Học tập"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(if (task.isCompleted) Color(0xFF2F5BEA) else Color.Transparent)
                .border(
                    width = if (task.isCompleted) 0.dp else 1.5.dp,
                    color = if (task.isCompleted) Color.Transparent else Color(0xFFD1D5DB),
                    shape = CircleShape
                )
                .clickable { onToggle() },
            contentAlignment = Alignment.Center
        ) {
            if (task.isCompleted) {
                Text(text = "✓", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            // Tiêu đề: 15 / normal · 500
            Text(
                text = task.title,
                fontSize = 15.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.W500,
                color = if (task.isCompleted) Color(0xFF9CA3AF) else Color(0xFF1F2937),
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            )

            if (!task.isCompleted) {
                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Chip danh mục: 12 / normal · 600
                    Text(
                        text = task.category.name,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isStudy) Color(0xFFEEF2FF) else Color(0xFFFFF4E5))
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                        color = if (isStudy) Color(0xFF526CE5) else Color(0xFFE69A36),
                        fontSize = 12.sp,
                        fontFamily = BeVietnamProFontFamily,
                        fontWeight = FontWeight.W600
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Thời gian: 12 / normal · 400
                    Text(
                        text = task.time,
                        color = Color(0xFF9CA3AF),
                        fontSize = 12.sp,
                        fontFamily = BeVietnamProFontFamily,
                        fontWeight = FontWeight.W400
                    )
                }
            }
        }

        // Tag "Gấp": 11 / normal · 600 (nếu ưu tiên HIGH)
        if (task.priority == TaskPriority.HIGH && !task.isCompleted) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFFEE2E2))
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = "Gấp",
                    fontSize = 11.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W600,
                    color = Color(0xFFDC2626)
                )
            }
        }
    }
}

// Dữ liệu mẫu khớp thiết kế
private val sampleTasks = listOf(
    Task(1, "Ôn chương 3 – Cấu trúc dữ liệu", studyCategory, "Hôm nay, 14:00", false, TaskPriority.MEDIUM),
    Task(2, "Đi chợ mua đồ cho tuần", lifeCategory, "Hôm nay, 17:30", false, TaskPriority.LOW),
    Task(3, "Gọi điện về nhà", lifeCategory, "Ngày mai, 21:00", false, TaskPriority.LOW),
    Task(4, "Họp nhóm đồ án", studyCategory, "Tuần này, 20:00", false, TaskPriority.HIGH),
    Task(5, "Tập thể dục 30 phút", lifeCategory, "06:30", true, TaskPriority.LOW),
    Task(6, "Nộp bài tập Lập trình di động", studyCategory, "09:00", true, TaskPriority.HIGH)
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TaskListScreenPreview() {
    TaskListScreen()
}