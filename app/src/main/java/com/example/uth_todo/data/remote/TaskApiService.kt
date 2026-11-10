package com.example.uth_todo.data.remote

import com.example.uth_todo.data.remote.model.TaskResponse
import com.example.uth_todo.data.remote.model.TaskUpdateRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface TaskApiService {

    // Lấy danh sách công việc
    @GET("tasks")
    suspend fun getTasks(
        @Header("Authorization") authorization: String
    ): List<TaskResponse>

    // Cập nhật trạng thái công việc
    @PATCH("tasks/{id}")
    suspend fun updateTask(
        @Path("id") id: Int,
        @Header("Authorization") authorization: String,
        @Body request: TaskUpdateRequest
    ): TaskResponse
}