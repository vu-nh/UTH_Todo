package com.example.uth_todo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uth_todo.config.ApiConfig
import com.example.uth_todo.data.studyCategory
import com.example.uth_todo.data.lifeCategory
import com.example.uth_todo.data.remote.model.toTask
import com.example.uth_todo.ui.viewmodel.TaskViewModel
import com.example.uth_todo.ui.components.home.*
import com.example.uth_todo.ui.theme.BeVietnamProFontFamily
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import com.example.uth_todo.R

// Lấy ngày hiện tại theo múi giờ Việt Nam
private fun getTodayDate(): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        .apply {
            timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
        }
        .format(Date())
}

// Chuẩn hóa ngày API về định dạng yyyy-MM-dd
private fun normalizeApiDate(value: String): String? {
    val date = value.trim()

    // Trường hợp yyyy-MM-dd hoặc yyyy-MM-ddTHH:mm:ss
    if (date.matches(Regex("^\\d{4}-\\d{2}-\\d{2}.*"))) {
        return date.take(10)
    }

    // Hỗ trợ thêm định dạng dd/MM/yyyy và dd-MM-yyyy
    val formats = listOf("dd/MM/yyyy", "dd-MM-yyyy")

    for (pattern in formats) {
        try {
            val parser = SimpleDateFormat(pattern, Locale.getDefault()).apply {
                isLenient = false
                timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
            }

            val parsedDate = parser.parse(date) ?: continue

            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .apply {
                    timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
                }
                .format(parsedDate)

        } catch (_: Exception) {
            // Thử định dạng tiếp theo
        }
    }

    return null
}

@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit = {}
) {
    val taskViewModel: TaskViewModel = viewModel()

    val apiTasks by taskViewModel.tasks.collectAsState()
    val isLoading by taskViewModel.isLoading.collectAsState()
    val errorMessage by taskViewModel.errorMessage.collectAsState()

    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current

    // Gọi API khi mở Home
    LaunchedEffect(Unit) {
        if (!isPreview) {
            taskViewModel.loadTasks(ApiConfig.MOCK_API_TOKEN)
        }
    }

    // Ngày hiện tại
    val todayDate = getTodayDate()

    // Toàn bộ công việc
    val allTasks = apiTasks.map { it.toTask() }

    // Chuẩn hóa ngày API rồi lọc công việc hôm nay
    val todayApiTasks = apiTasks
        .filter { normalizeApiDate(it.date) == todayDate }
        .sortedBy { it.time }

    val todayTasks = todayApiTasks.map { it.toTask() }

    // Tính tiến độ hôm nay
    val completedTasks = todayApiTasks.count { it.done }
    val totalTasks = todayApiTasks.size

    var selectedItem by remember {
        mutableStateOf("Trang chủ")
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedItem,
                onItemSelected = {
                    selectedItem = it

                    if (it == "Cài đặt") {
                        onNavigateToProfile()
                    }
                },
                onAddClick = {
                    // Xử lý thêm công việc sau
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
                .background(Color(0xFFF5F5F7))
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        ) {

            // Header: tự động hiển thị ngày hiện tại
            HomeHeader()

            Spacer(modifier = Modifier.height(20.dp))

            // Tiến độ hôm nay
            ProgressCard(
                completedTasks = completedTasks,
                totalTasks = totalTasks
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Học tập
                CategoryCard(
                    modifier = Modifier.weight(1f),
                    categoryName = studyCategory.name,
                    remainingTasks = allTasks.count {
                        it.category.id == studyCategory.id && !it.isCompleted
                    },
                    iconPainter = painterResource(id = R.drawable.home_icon1),
                    backgroundColor = Color(0xFFFFFFFF),
                    iconBackgroundColor = Color(0xFFE6EDFF)
                )

                // Cuộc sống
                CategoryCard(
                    modifier = Modifier.weight(1f),
                    categoryName = lifeCategory.name,
                    remainingTasks = allTasks.count {
                        it.category.id == lifeCategory.id && !it.isCompleted
                    },
                    iconPainter = painterResource(id = R.drawable.home_icon2),
                    backgroundColor = Color(0xFFFFFFFF),
                    iconBackgroundColor = Color(0xFFFFEBD9)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Danh sách công việc hôm nay (Đã có "Xem tất cả" chuẩn Figma)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Việc hôm nay",
                    fontSize = 19.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W700,
                    color = Color(0xFF1F2937)
                )

                Text(
                    text = "Xem tất cả",
                    fontSize = 14.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFF2F5BEA),
                    modifier = Modifier.clickable {
                        // Thêm hành động bấm xem tất cả ở đây
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Đang tải dữ liệu
            if (isLoading && todayTasks.isEmpty()) {
                Text(
                    text = "Đang tải công việc...",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = BeVietnamProFontFamily
                )
            }

            // Hiển thị lỗi và thử tải lại
            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "Đã xảy ra lỗi",
                    color = Color(0xFFDC2626),
                    fontSize = 13.sp,
                    fontFamily = BeVietnamProFontFamily
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Thử lại",
                    color = Color(0xFF2F5BEA),
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        taskViewModel.loadTasks(
                            ApiConfig.MOCK_API_TOKEN
                        )
                    }
                )
            }

            // Chưa có công việc hôm nay
            if (
                !isLoading &&
                errorMessage == null &&
                todayTasks.isEmpty()
            ) {
                Text(
                    text = "Hôm nay chưa có việc nào. Bấm + để thêm nhé.",
                    color = Color(0xFF9CA3AF),
                    fontSize = 13.sp,
                    fontFamily = BeVietnamProFontFamily
                )
            }

            // Danh sách công việc
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                todayTasks.forEach { task ->
                    TaskItem(
                        task = task,
                        onToggle = {
                            taskViewModel.updateTask(
                                token = ApiConfig.MOCK_API_TOKEN,
                                id = task.id,
                                done = !task.isCompleted,
                                onSuccess = {
                                    if (!task.isCompleted) {
                                        Toast.makeText(
                                            context,
                                            "Tuyệt, đã xong một việc!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}