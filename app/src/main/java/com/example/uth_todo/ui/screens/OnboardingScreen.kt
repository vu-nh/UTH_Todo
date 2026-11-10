package com.example.uth_todo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uth_todo.R
import com.example.uth_todo.data.onboardingPages
import com.example.uth_todo.ui.components.onboarding.CategoryIllustration
import com.example.uth_todo.ui.components.onboarding.ChecklistIllustration
import com.example.uth_todo.ui.components.onboarding.ReminderIllustration
import com.example.uth_todo.ui.theme.BeVietnamProFontFamily
import androidx.compose.runtime.remember

@Composable
fun OnboardingScreen(
    onNavigateToLogin: () -> Unit = {}
) {
    var currentPage by remember {
        mutableIntStateOf(0)
    }

    val page = onboardingPages[currentPage]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(
                horizontal = 28.dp,
                vertical = 20.dp
            )
    ) {
        // 1. HEADER
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo nhỏ
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF2F5BEA)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.logo_uth_boarding
                    ),
                    contentDescription = "UTH Todo Logo",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Tên ứng dụng
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "UTH",
                    fontSize = 19.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W700,
                    color = Color(0xFF1C1C1E)
                )

                Text(
                    text = " Todo",
                    fontSize = 19.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFF2F5BEA)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Nút bỏ qua
            TextButton(
                onClick = onNavigateToLogin,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Bỏ qua",
                    fontSize = 15.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFF6B6B70)
                )
            }
        }

        Spacer(modifier = Modifier.height(106.dp))

        // 2. Các biểu tượng của Ondoarding
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            when (currentPage) {
                0 -> ChecklistIllustration()
                1 -> CategoryIllustration()
                2 -> ReminderIllustration()
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // 3. TITLE
        Text(
            text = page.title,
            fontSize = 28.sp,
            fontFamily = BeVietnamProFontFamily,
            fontWeight = FontWeight.W700,
            color = Color(0xFF1C1C1E),
            lineHeight = 36.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 4. DESCRIPTION
        Text(
            text = page.description,
            fontSize = 16.sp,
            fontFamily = BeVietnamProFontFamily,
            fontWeight = FontWeight.W400,
            color = Color(0xFF6B6B70),
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 5. Chỉ báo trang - Dấu chấm
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            onboardingPages.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            // Cập nhật trực tiếp currentPage thành index của chấm được bấm
                            currentPage = index
                        }
                        .size(
                            width = if (currentPage == index) {
                                24.dp
                            } else {
                                8.dp
                            },
                            height = 8.dp
                        )
                        .clip(RoundedCornerShape(50))
                        .background(
                            if (currentPage == index) {
                                Color(0xFF2F5BEA)
                            } else {
                                Color(0xFFC7C7CC)
                            }
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 6. BUTTON Tiếp tục
        Button(
            onClick = {
                if (currentPage < onboardingPages.lastIndex) {
                    currentPage++
                } else {
                    onNavigateToLogin()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2F5BEA)
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = if (currentPage < onboardingPages.lastIndex) {
                    page.buttonText
                } else {
                    "Bắt đầu"
                },
                fontSize = 17.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.W600,
                color = Color(0xFFFFFFFF)
            )
        }
    }
}
