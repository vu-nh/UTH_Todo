package com.example.uthtodov2.ui.screens

import com.example.uthtodov2.data.splashData
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.uthtodov2.ui.theme.BeVietnamPro

@Composable
fun SplashScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF2F5BEA)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))

            // Logo
            Image(
                painterResource(R.drawable.logo_uth_todo),
                contentDescription = "App icon",
                modifier = Modifier
                    .padding(bottom = 22.dp)
            )
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = BeVietnamPro,
                                fontWeight = FontWeight.ExtraBold
                            )
                        ) {
                            append("UTH")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontFamily = BeVietnamPro,
                                fontWeight = FontWeight.Medium,
                            )
                        ) {
                            append(" Todo")
                        }
                    },
                    fontSize = 40.sp,
                    color = Color.White,
                    lineHeight = 46.sp
                )

                Text(
                    text = splashData.tagline,
                    modifier = Modifier
                        .padding(top = 8.dp),
                    color = Color.White.copy(alpha = 0.9f),
                    fontFamily = BeVietnamPro,
                    fontSize = 15.sp,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                        .padding(bottom = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Thanh tiến độ mờ
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(4.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.28f),
                            shape = RoundedCornerShape(80)
                        )
                ) {
                    // Thanh Tiến độ bình thường
                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(4.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(80)
                            )
                    )
                }
                // Phiên bản
                Text(
                    text = "Phiên bản ${splashData.version}",
                    modifier = Modifier.padding(top = 12.dp),
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 12.sp,
                    fontFamily = BeVietnamPro,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    SplashScreen()
}