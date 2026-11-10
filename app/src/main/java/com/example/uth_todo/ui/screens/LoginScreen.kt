package com.example.uth_todo.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.example.uth_todo.R
import com.example.uth_todo.ui.theme.BeVietnamProFontFamily
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit = {},
    onGuestClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFFFFF)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 28.dp)
        ) {

            // Khoảng trống phía trên
            Spacer(modifier = Modifier.weight(1f))

            // Phần giới thiệu
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .background(Color(0xFF2F5BEA)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_login),
                        contentDescription = "UTH Todo Logo Login",
                        modifier = Modifier.size(88.dp)
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                // Specs Figma: 30 / 38 · 700
                Text(
                    text = "Chào mừng đến với\nUTH Todo",
                    fontSize = 30.sp,
                    lineHeight = 38.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W700,
                    color = Color(0xFF1C1C1E)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Specs Figma: 16 / 24 · 400
                Text(
                    text = "Đăng nhập để lưu công việc và đồng bộ\ntrên mọi thiết bị của bạn.",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = BeVietnamProFontFamily,
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF6B6B70)
                )
            }

            // Khoảng trống giữa nội dung và nút
            Spacer(modifier = Modifier.weight(1.2f))

            // Nút đăng nhập Google đã được nối với Firebase Sign-In
            GoogleLoginButton(
                onClick = {
                    handleFirebaseGoogleSignIn(
                        context = context,
                        coroutineScope = coroutineScope,
                        onLoginSuccess = { _, _ ->
                            onNavigateToHome()
                        }
                    )
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Đường phân cách "hoặc"
            OrDivider()

            Spacer(modifier = Modifier.height(14.dp))

            // Dùng thử không cần tài khoản - Specs Figma: 16 / normal · 600
            Text(
                text = "Dùng thử không cần tài khoản",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onGuestClick() }
                    .padding(vertical = 6.dp),
                fontSize = 16.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.W600,
                color = Color(0xFF2F5BEA),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Điều khoản và chính sách - Specs Figma: 12 / 18 · 400
            Text(
                text = "Khi tiếp tục, bạn đồng ý với Điều khoản sử dụng và Chính\nsách quyền riêng tư của UTH Todo.",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.W400,
                color = Color(0xFF6B6B70),
                textAlign = TextAlign.Center
            )
        }
    }
}

// Nút đăng nhập Google chuẩn Spec Figma
@Composable
private fun GoogleLoginButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFFE5E5EA),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFE5E5EA),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "G",
                fontSize = 12.sp,
                fontFamily = BeVietnamProFontFamily,
                fontWeight = FontWeight.W700,
                color = Color(0xFF6B6B70)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Tiếp tục với Google",
            fontSize = 17.sp,
            fontFamily = BeVietnamProFontFamily,
            fontWeight = FontWeight.W600,
            color = Color(0xFF1C1C1E)
        )
    }
}

// Đường phân cách chuẩn Spec Figma
@Composable
private fun OrDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color(0xFFEDEDF0)
        )

        Text(
            text = "hoặc",
            modifier = Modifier.padding(horizontal = 12.dp),
            fontSize = 13.sp,
            fontFamily = BeVietnamProFontFamily,
            fontWeight = FontWeight.W400,
            color = Color(0xFF6B6B70)
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color(0xFFEDEDF0)
        )
    }
}

fun handleFirebaseGoogleSignIn(
    context: Context,
    coroutineScope: CoroutineScope,
    onLoginSuccess: (email: String, name: String) -> Unit
) {
    val credentialManager = CredentialManager.create(context)
    val auth = Firebase.auth

    val webClientId = "288736250202-r0g8en7dr7jnl5lh4gppahcaqas69c6t.apps.googleusercontent.com"

    val googleIdOption = GetGoogleIdOption.Builder()
        .setServerClientId(webClientId)
        .setFilterByAuthorizedAccounts(false)
        .build()

    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    coroutineScope.launch(Dispatchers.IO) {
        launch(Dispatchers.Main) {
            Toast.makeText(context, "Đang kết nối...", Toast.LENGTH_SHORT).show()
        }

        try {
            val result = credentialManager.getCredential(
                context = context,
                request = request
            )

            val credential = result.credential
            if (credential is androidx.credentials.CustomCredential &&
                credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val idToken = googleIdTokenCredential.idToken

                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)

                auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val userEmail = user?.email ?: ""
                            val userName = user?.displayName ?: "Người dùng"

                            Toast.makeText(context, "Đã đăng nhập: $userEmail", Toast.LENGTH_SHORT).show()
                            onLoginSuccess(userEmail, userName)
                        } else {
                            Toast.makeText(
                                context,
                                "Đăng nhập Firebase thất bại: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        } catch (e: GetCredentialException) {
            launch(Dispatchers.Main) {
                Toast.makeText(context, "Đã hủy hoặc thất bại: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            launch(Dispatchers.Main) {
                Toast.makeText(context, "Lỗi không xác định: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "UTH Todo - Login"
)
@Composable
private fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
}