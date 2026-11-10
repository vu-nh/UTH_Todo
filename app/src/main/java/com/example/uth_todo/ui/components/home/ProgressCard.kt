
package com.example.uth_todo.ui.components.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.uth_todo.ui.theme.BeVietnamProFontFamily

@Composable
fun ProgressCard(
    completedTasks: Int,
    totalTasks: Int
) {
    // Tính tỷ lệ hoàn thành
    val progress = if (totalTasks > 0) {
        (completedTasks.toFloat() / totalTasks).coerceIn(0f, 1f)
    } else {
        0f
    }

    // Chọn lời nhắn theo tiến độ
    val progressMessage = when {
        progress >= 1f -> "Xong hết rồi, nghỉ ngơi thôi!"
        progress >= 0.5f -> "Đã hơn nửa chặng, cố lên!"
        else -> "Bắt đầu từ việc nhỏ nhất nhé."
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(121.dp)
            .background(
                Color(0xFF2F5BEA),
                shape = RoundedCornerShape(22.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Thông tin tiến độ
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Tiến độ hôm nay",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W400,
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "$completedTasks/$totalTasks việc",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = BeVietnamProFontFamily
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = progressMessage,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W400
                )
            }

            // Vòng tròn tiến độ
            Box(
                modifier = Modifier.size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val strokeWidth = 6.dp.toPx()

                    // Vòng nền
                    drawArc(
                        color = Color(0xFF8299F5),
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = androidx.compose.ui.graphics.drawscope.Stroke(
                            width = strokeWidth
                        )
                    )

                    // Vòng tiến độ
                    drawArc(
                        color = Color.White,
                        startAngle = -90f,
                        sweepAngle = 360f * progress,
                        useCenter = false,
                        style = androidx.compose.ui.graphics.drawscope.Stroke(
                            width = strokeWidth,
                            cap = StrokeCap.Butt
                        )
                    )
                }

                Text(
                    text = "${(progress * 100).toInt()}%",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressCardPreview() {
    ProgressCard(
        completedTasks = 2,
        totalTasks = 4
    )
}
