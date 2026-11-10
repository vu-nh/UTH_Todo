package com.example.uth_todo.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uth_todo.R
import com.example.uth_todo.ui.theme.BeVietnamProFontFamily
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(
    onNavigateToOnboarding: () -> Unit
) {
    // Chờ 2 giây rồi chuyển sang Onboarding
    LaunchedEffect(Unit) {
        delay(2.seconds)
        onNavigateToOnboarding()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F5BEA))
    ) {
        // Box chứa logo và tên ứng dụng
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Logo
                Box(
                    modifier = Modifier
                        .size(116.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.logo_uth
                        ),
                        contentDescription = "UTH Todo Logo",
                        modifier = Modifier.size(116.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tên ứng dụng
                Row {
                    Text(
                        text = "UTH",
                        color = Color.White,
                        fontSize = 40.sp,
                        fontFamily = BeVietnamProFontFamily,
                        fontWeight = FontWeight.W800
                    )

                    Text(
                        text = " Todo",
                        color = Color.White,
                        fontSize = 40.sp,
                        fontFamily = BeVietnamProFontFamily,
                        fontWeight = FontWeight.W500
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Việc học, việc nhà — gọn gàng mỗi ngày",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W400
                )
            }
        }

        // Loading và phiên bản
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Thanh Loading
//            SplashLoadingIndicator()

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Phiên bản 1.0",
                color = Color.White,
                fontSize = 12.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.W400
            )
        }
    }
}

//// Thanh Loading chạy liên tục
//@Preview
//@Composable
//fun SplashLoadingIndicator() {
//    var loading by remember { mutableStateOf(false) }
//
//    Button(onClick = { loading = true }, enabled = !loading) {
//        Text("Start loading")
//    }
//
//    if (!loading) return
//
//    LinearProgressIndicator(
//        modifier = Modifier.fillMaxWidth(),
//        color = MaterialTheme.colorScheme.secondary,
//        trackColor = MaterialTheme.colorScheme.surfaceVariant,
//    )
//}