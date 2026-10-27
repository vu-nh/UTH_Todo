
package com.example.uthtodov2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uthtodov2.R
import com.example.uthtodov2.data.onboardingPages
import com.example.uthtodov2.ui.theme.BeVietnamPro

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit = {}
) {
    var currentPage by remember {
        mutableIntStateOf(0)
    }

    val page = onboardingPages[currentPage]

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.logo_uth_text
                        ),
                        contentDescription = "Logo UTH Todo"
                    )

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontFamily = BeVietnamPro,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = TextBlack
                                )
                            ) {
                                append("UTH")
                            }

                            withStyle(
                                SpanStyle(
                                    fontFamily = BeVietnamPro,
                                    fontWeight = FontWeight.Medium,
                                    color = PrimaryBlue
                                )
                            ) {
                                append(" Todo")
                            }
                        },
                        modifier = Modifier.padding(start = 4.dp),
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "Bỏ qua",
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            currentPage = onboardingPages.lastIndex
                        }
                        .padding(6.dp),
                    fontFamily = BeVietnamPro,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    color = TextGray
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            // Hình minh họa thay đổi theo trang
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                when (currentPage) {
                    0 -> TaskPreviewIllustration()
                    1 -> CategoryPreviewIllustration()
                    2 -> ReminderPreviewIllustration()
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Nội dung lấy từ đối tượng OnboardingPage
            Column {
                Text(
                    text = page.title,
                    fontFamily = BeVietnamPro,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    lineHeight = 26.sp,
                    color = TextBlack
                )

                Text(
                    text = page.description,
                    modifier = Modifier.padding(top = 8.dp),
                    fontFamily = BeVietnamPro,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = TextGray
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Chỉ báo trang
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(onboardingPages.size) { index ->
                    Box(
                        modifier = Modifier
                            .then(
                                if (index == currentPage) {
                                    Modifier
                                        .width(17.dp)
                                        .height(6.dp)
                                } else {
                                    Modifier.size(6.dp)
                                }
                            )
                            .clip(CircleShape)
                            .background(
                                if (index == currentPage) {
                                    PrimaryBlue
                                } else {
                                    Color(0xFFD1D5DB)
                                }
                            )
                    )
                }
            }

            // Nút hành động
            Button(
                onClick = {
                    if (currentPage < onboardingPages.lastIndex) {
                        currentPage++
                    } else {
                        onFinish()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 28.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue
                )
            ) {
                Text(
                    text = page.buttonText,
                    fontFamily = BeVietnamPro,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun OnboardingPreview() {
    OnboardingScreen()
}
