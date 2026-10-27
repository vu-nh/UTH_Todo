package com.example.uthtodov2.data

data class SplashData(
    val appName: String,
    val tagline: String,
    val version: String
)

val splashData = SplashData(
    appName = "UTH Todo",
    tagline = "Việc học, việc nhà — gọn gàng mỗi ngày",
    version = "2.1.0"
)
