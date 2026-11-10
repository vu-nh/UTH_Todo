package com.example.uth_todo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colors theo Design Share
private val ProfileBackground = Color(0xFFF5F5F7)
private val ProfileBlue = Color(0xFF2F5BEA)
private val ProfileText = Color(0xFF1C1C1E)
private val ProfileSecondary = Color(0xFF6B6B70)
private val ProfileBorder = Color(0xFFD8D8DE)
private val ProfileRed = Color(0xFFC4321F)
private val ProfileLightBlue = Color(0xFFE6EDFF)
private val ProfileBarBlue = Color(0xFF2F5BEA)
private val ProfileBarLightBlue = Color(0xFF2F5BEA66)

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {},
    onLinkGoogleClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ProfileBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            // 1. HEADER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "‹ Cài đặt",
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onBackClick() },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = ProfileBlue
                )

                Text(
                    text = "Hồ sơ",
                    modifier = Modifier.weight(1f),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfileText,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            // 2. PROFILE INFORMATION
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(74.dp)
                        .clip(CircleShape)
                        .background(ProfileLightBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "K",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Medium,
                        color = ProfileBlue
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Khách",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfileText
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Chưa liên kết tài khoản",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = ProfileSecondary
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Google link button
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(11.dp))
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = ProfileBorder,
                            shape = RoundedCornerShape(11.dp)
                        )
                        .clickable {
                            onLinkGoogleClick()
                        }
                        .padding(
                            horizontal = 13.dp,
                            vertical = 8.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(19.dp)
                            .border(
                                1.dp,
                                ProfileBorder,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "G",
                            fontSize = 11.sp,
                            color = ProfileSecondary
                        )
                    }

                    Spacer(modifier = Modifier.width(7.dp))

                    Text(
                        text = "Liên kết Google",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ProfileText
                    )
                }
            }

            // 3. TASK STATISTICS
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProfileStatCard(
                    value = "2",
                    label = "Việc đã xong",
                    modifier = Modifier.weight(1f)
                )

                ProfileStatCard(
                    value = "4",
                    label = "Đang chờ",
                    modifier = Modifier.weight(1f)
                )

                ProfileStatCard(
                    value = "33%",
                    label = "Hoàn thành",
                    modifier = Modifier.weight(1f)
                )
            }

            // 4. WEEKLY CHART
            WeeklyProgressCard(
                modifier = Modifier.padding(top = 12.dp)
            )

            // Push logout button to bottom
            Spacer(modifier = Modifier.weight(1f))

            // 5. LOGOUT BUTTON
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                    .clickable {
                        onLogoutClick()
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "⇥",
                    fontSize = 20.sp,
                    color = ProfileRed
                )

                Spacer(modifier = Modifier.width(9.dp))

                Text(
                    text = "Đăng xuất",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ProfileRed
                )
            }
        }
    }
}

// Statistics card
@Composable
private fun ProfileStatCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(64.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(
                horizontal = 10.dp,
                vertical = 8.dp
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = value,
            fontSize = 20.sp,
            lineHeight = 23.sp,
            fontWeight = FontWeight.Bold,
            color = ProfileText
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = label,
            fontSize = 10.sp,
            color = ProfileSecondary,
            maxLines = 1
        )
    }
}

// Weekly progress chart
@Composable
private fun WeeklyProgressCard(
    modifier: Modifier = Modifier
) {
    val weeklyData = listOf(
        WeeklyBar("T2", 0.75f),
        WeeklyBar("T3", 0.50f),
        WeeklyBar("T4", 0f),
        WeeklyBar("T5", 0f),
        WeeklyBar("T6", 0f),
        WeeklyBar("T7", 0f),
        WeeklyBar("CN", 0f)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(
                horizontal = 13.dp,
                vertical = 12.dp
            )
    ) {
        // Chart header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tuần này",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = ProfileText
            )

            Text(
                text = "Việc xong mỗi ngày",
                fontSize = 11.sp,
                color = ProfileSecondary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Chart bars
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            weeklyData.forEachIndexed { index, item ->
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .width(22.dp)
                            .height(
                                when (index) {
                                    0 -> 36.dp
                                    1 -> 24.dp
                                    else -> 5.dp
                                }
                            )
                            .clip(RoundedCornerShape(5.dp))
                            .background(
                                when (index) {
                                    0 -> ProfileBarLightBlue
                                    1 -> ProfileBarBlue
                                    else -> Color(0xFFE9E9EE)
                                }
                            )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.day,
                        fontSize = 10.sp,
                        fontWeight = if (index == 1) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        },
                        color = if (index == 1) {
                            ProfileText
                        } else {
                            ProfileSecondary
                        }
                    )
                }
            }
        }
    }
}

private data class WeeklyBar(
    val day: String,
    val progress: Float
)

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "UTH Todo - Profile"
)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}
