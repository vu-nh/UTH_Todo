package com.example.uth_todo.data

enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH;

    companion object {
        fun fromApi(value: String?): TaskPriority {
            return when (value?.lowercase()) {
                "low" -> LOW
                "high" -> HIGH
                else -> MEDIUM
            }
        }
    }
}