package com.example.uth_todo.data

data class OnboardingPage(
    val title: String,
    val description: String,
    val buttonText: String
)

val onboardingPages = listOf(
    OnboardingPage(
        title = "Mọi việc, gọn trong một chỗ",
        description = "Ghi lại bài tập, việc nhà, lịch hẹn. Không còn quên việc nào.",
        buttonText = "Tiếp tục"
    ),
    OnboardingPage(
        title = "Chia theo Học tập và Cuộc sống",
        description = "Mỗi việc có nhóm riêng, nhìn là biết hôm nay cần lo gì.",
        buttonText = "Tiếp tục"
    ),
    OnboardingPage(
        title = "Nhắc đúng lúc",
        description = "Bật thông báo để UTH Todo nhắc bạn trước hạn chót.",
        buttonText = "Bắt đầu ngay"
    )
)