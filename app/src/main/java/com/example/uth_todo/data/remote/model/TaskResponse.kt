package com.example.uth_todo.data.remote.model

data class TaskResponse(
    val id: Int,
    val title: String,
    val note: String,
    val category: String,
    val date: String,
    val time: String,
    val priority: String,
    val remind: Boolean,
    val done: Boolean,
    val subtasks: List<SubtaskResponse>
)
