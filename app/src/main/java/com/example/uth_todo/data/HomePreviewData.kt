package com.example.uth_todo.data

// Hai danh mục dùng chung
val studyCategory = Category(
    id = 1,
    name = "Học tập"
)

val lifeCategory = Category(
    id = 2,
    name = "Cuộc sống"
)

// Danh sách công việc mẫu
val homeTasks = listOf(
    Task(
        id = 1,
        title = "Tập thể dục 30 phút",
        category = lifeCategory,
        time = "06:30",
        isCompleted = true
    ),
    Task(
        id = 2,
        title = "Nộp bài tập Lập trình di động",
        category = studyCategory,
        time = "09:00",
        isCompleted = true
    ),
    Task(
        id = 3,
        title = "Ôn chương 3 - Cấu trúc dữ liệu",
        category = studyCategory,
        time = "14:00",
        isCompleted = false
    ),
    Task(
        id = 4,
        title = "Đi chợ mua đồ cho tuần",
        category = lifeCategory,
        time = "17:30",
        isCompleted = false
    )
)
