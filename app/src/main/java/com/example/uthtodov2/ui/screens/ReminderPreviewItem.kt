package com.example.uthtodov2.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uthtodov2.ui.theme.BeVietnamPro

@Composable
fun ReminderPreviewIllustration() {
    Box(
        modifier = Modifier
            .size(width = 200.dp, height = 236.dp)
            .background(
                Color(0xFFE9EDFF),
                RoundedCornerShape(24.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier.size(34.dp)
                ) {
                    val bell = Path().apply {
                        moveTo(size.width * 0.5f, size.height * 0.12f)
                        cubicTo(
                            size.width * 0.25f, size.height * 0.12f,
                            size.width * 0.22f, size.height * 0.38f,
                            size.width * 0.22f, size.height * 0.55f
                        )
                        lineTo(size.width * 0.12f, size.height * 0.78f)
                        lineTo(size.width * 0.88f, size.height * 0.78f)
                        lineTo(size.width * 0.78f, size.height * 0.55f)
                        cubicTo(
                            size.width * 0.78f, size.height * 0.38f,
                            size.width * 0.75f, size.height * 0.12f,
                            size.width * 0.5f, size.height * 0.12f
                        )
                    }

                    drawPath(
                        path = bell,
                        color = Color(0xFF315CF5),
                        style = Stroke(
                            width = 2.2.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    )

                    drawLine(
                        color = Color(0xFF315CF5),
                        start = Offset(
                            size.width * 0.42f,
                            size.height * 0.87f
                        ),
                        end = Offset(
                            size.width * 0.58f,
                            size.height * 0.87f
                        ),
                        strokeWidth = 2.2.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }

            Text(
                text = "Ôn bài Toán lúc 19:00",
                modifier = Modifier
                    .background(
                        Color.White,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(
                        horizontal = 12.dp,
                        vertical = 10.dp
                    ),
                fontFamily = BeVietnamPro,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                color = Color(0xFF222222)
            )
        }
    }
}
