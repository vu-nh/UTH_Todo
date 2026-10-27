
package com.example.uthtodov2.data

data class OnboardingPage(
    val title: String,
    val description: String,
    val pageNumber: Int,
    val buttonText: String
)

val onboardingPages = listOf(
    OnboardingPage(
        title = "Mọi việc, gọn trong một chỗ",
        description = "Ghi lại bài tập, việc nhà, lịch hẹn. Không còn quên việc nào.",
        pageNumber = 1,
        buttonText = "Tiếp tục"
    ),
    OnboardingPage(
        title = "Chia theo Học tập và Cuộc sống",
        description = "Mỗi việc có nhóm riêng, nhìn là biết hôm nay cần lo gì.",
        pageNumber = 2,
        buttonText = "Tiếp tục"
    ),
    OnboardingPage(
        title = "Nhắc đúng lúc",
        description = "Bật thông báo để UTH Todo nhắc bạn trước hạn chót.",
        pageNumber = 3,
        buttonText = "Bắt đầu ngay"
    )
)
