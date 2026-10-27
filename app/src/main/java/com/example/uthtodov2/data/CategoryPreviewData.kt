package com.example.uthtodov2.data

data class CategoryPreviewData(
    val label: String,
    val lineWidth: Int
)

val categoryPreviewItems = listOf(
    CategoryPreviewData(
        label = "Học tập",
        lineWidth = 102
    ),
    CategoryPreviewData(
        label = "Cuộc sống",
        lineWidth = 72
    )
)
