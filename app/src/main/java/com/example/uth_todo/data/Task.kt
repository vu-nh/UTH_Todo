package com.example.uth_todo.data

data class Task(
    val id: Int,
    val title: String,
    val category: Category,
    val time: String,
    val isCompleted: Boolean,
    val priority: TaskPriority = TaskPriority.MEDIUM
)
