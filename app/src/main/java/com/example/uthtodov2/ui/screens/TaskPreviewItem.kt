package com.example.uthtodov2.ui.screens

import com.example.uthtodov2.data.categoryPreviewItems
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uthtodov2.ui.theme.BeVietnamPro


val TextBlack = Color(0xFF222222)

val TextGray = Color(0xFF6B6B70)
val PrimaryBlue = Color(0xFF2F5BEA)
private val IllustrationBackground = Color(0xFFE9EDFF)
private val LineGray = Color(0xFFE3E4EA)
private val CircleGray = Color(0xFFC7C9D1)

data class TaskPreviewData(
    val isCompleted: Boolean,
    val lineWidth: Int
)

@Composable
fun TaskPreviewItem(task: TaskPreviewData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(13.dp),
                ambientColor = Color(0x12000000),
                spotColor = Color(0x12000000)
            )
            .clip(RoundedCornerShape(13.dp))
            .background(Color.White)
            .padding(horizontal = 13.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Trạng thái công việc
        Box(
            modifier = Modifier
                .size(20.dp)
                .then(
                    if (task.isCompleted) {
                        Modifier
                            .clip(CircleShape)
                            .background(PrimaryBlue)
                    } else {
                        Modifier
                            .clip(CircleShape)
                            .border(1.5.dp, CircleGray, CircleShape)
                    }
                ),
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

        // Dòng đại diện cho nội dung công việc
        Box(
            modifier = Modifier
                .width(task.lineWidth.dp)
                .height(6.dp)
                .clip(CircleShape)
                .background(LineGray)
        )
    }
}

@Composable
fun TaskPreviewIllustration() {
    val tasks = listOf(
        TaskPreviewData(isCompleted = true, lineWidth = 84),
        TaskPreviewData(isCompleted = false, lineWidth = 62),
        TaskPreviewData(isCompleted = false, lineWidth = 92)
    )

    Box(
        modifier = Modifier
            .size(width = 200.dp, height = 236.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(IllustrationBackground)
            .padding(horizontal = 9.dp),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.foundation.layout.Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            tasks.forEach { task ->
                TaskPreviewItem(task = task)
            }
        }
    }
}

@Composable
fun CategoryPreviewIllustration() {
    Box(
        modifier = Modifier
            .size(width = 200.dp, height = 236.dp)
            .background(
                color = Color(0xFFE9EDFF),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            categoryPreviewItems.forEach { category ->
                when (category.label) {
                    "Học tập" -> CategoryCard(
                        label = category.label,
                        labelColor = Color(0xFFDCE7FF),
                        textColor = Color(0xFF2456D8),
                        lineWidth = category.lineWidth.dp
                    )

                    "Cuộc sống" -> CategoryCard(
                        label = category.label,
                        labelColor = Color(0xFFFFE9D9),
                        textColor = Color(0xFFB94E0A),
                        lineWidth = category.lineWidth.dp
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryCard(
    label: String,
    labelColor: Color,
    textColor: Color,
    lineWidth: androidx.compose.ui.unit.Dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 10.dp
            ),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier
                .background(
                    color = labelColor,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(
                    horizontal = 8.dp,
                    vertical = 3.dp
                ),
            fontFamily = BeVietnamPro,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            color = textColor,
            lineHeight = 12.sp
        )

        Box(
            modifier = Modifier
                .width(lineWidth)
                .height(5.dp)
                .background(
                    color = Color(0xFFE2E3E9),
                    shape = RoundedCornerShape(10.dp)
                )
        )
    }
}