package com.example.uth_todo.data.repository

import com.example.uth_todo.data.remote.RetrofitClient
import com.example.uth_todo.data.remote.model.TaskResponse
import com.example.uth_todo.data.remote.model.TaskUpdateRequest

class TaskRepository {

    // Lấy danh sách công việc
    suspend fun getTasks(token: String): List<TaskResponse> {
        return RetrofitClient.taskApiService.getTasks(
            authorization = "Bearer $token"
        )
    }

    // Cập nhật trạng thái công việc
    suspend fun updateTask(
        token: String,
        id: Int,
        done: Boolean
    ): TaskResponse {
        return RetrofitClient.taskApiService.updateTask(
            id = id,
            authorization = "Bearer $token",
            request = TaskUpdateRequest(done = done)
        )
    }
}