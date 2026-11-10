package com.example.uth_todo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uth_todo.data.remote.model.TaskResponse
import com.example.uth_todo.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val repository = TaskRepository()

    // Danh sách công việc
    private val _tasks = MutableStateFlow<List<TaskResponse>>(emptyList())
    val tasks: StateFlow<List<TaskResponse>> = _tasks.asStateFlow()

    // Trạng thái tải dữ liệu
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Thông báo lỗi
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    // Lấy danh sách công việc từ API
    fun loadTasks(token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                _tasks.value = repository.getTasks(token)
            } catch (e: Exception) {
                _errorMessage.value =
                    e.message ?: "Không thể tải dữ liệu"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Cập nhật trạng thái công việc
    fun updateTask(
        token: String,
        id: Int,
        done: Boolean,
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch {
            _errorMessage.value = null

            try {
                val updatedTask = repository.updateTask(
                    token = token,
                    id = id,
                    done = done
                )

                // Cập nhật dữ liệu bằng kết quả server trả về
                _tasks.value = _tasks.value.map { task ->
                    if (task.id == id) updatedTask else task
                }

                onSuccess()

            } catch (e: Exception) {
                _errorMessage.value =
                    e.message ?: "Không thể cập nhật công việc"
            }
        }
    }
}