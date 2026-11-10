package com.example.uth_todo.data.remote.model

import com.example.uth_todo.data.Category
import com.example.uth_todo.data.Task
import com.example.uth_todo.data.TaskPriority

fun TaskResponse.toTask(): Task {

    val taskCategory = when (category) {
        "study" -> Category(1, "Học tập")
        "life" -> Category(2, "Cuộc sống")
        else -> Category(0, "Khác")
    }

    return Task(
        id = id,
        title = title,
        category = taskCategory,
        time = time,
        isCompleted = done,
        priority = TaskPriority.fromApi(priority)
    )
}