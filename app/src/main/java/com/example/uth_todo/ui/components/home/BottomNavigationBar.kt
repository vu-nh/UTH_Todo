package com.example.uth_todo.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigationBar(
    selectedItem: String = "Trang chủ",
    onItemSelected: (String) -> Unit = {},
    onAddClick: () -> Unit = {}
) {
    val primaryColor = Color(0xFF2F5BEA)
    val inactiveColor = Color(0xFF9CA3AF)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavigationItem(
            label = "Trang chủ",
            selected = selectedItem == "Trang chủ",
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Trang chủ")
            },
            onClick = { onItemSelected("Trang chủ") },
            activeColor = primaryColor,
            inactiveColor = inactiveColor
        )

        NavigationItem(
            label = "Danh sách",
            selected = selectedItem == "Danh sách",
            icon = {
                Icon(Icons.Default.CalendarMonth, contentDescription = "Danh sách")
            },
            onClick = { onItemSelected("Danh sách") },
            activeColor = primaryColor,
            inactiveColor = inactiveColor
        )

        // Nút thêm công việc ở giữa
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(primaryColor, CircleShape)
                .clickable { onAddClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Thêm công việc",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

        NavigationItem(
            label = "Thông báo",
            selected = selectedItem == "Thông báo",
            icon = {
                Icon(Icons.Default.Notifications, contentDescription = "Thông báo")
            },
            onClick = { onItemSelected("Thông báo") },
            activeColor = primaryColor,
            inactiveColor = inactiveColor
        )

        NavigationItem(
            label = "Cài đặt",
            selected = selectedItem == "Cài đặt",
            icon = {
                Icon(Icons.Default.Settings, contentDescription = "Cài đặt")
            },
            onClick = { onItemSelected("Cài đặt") },
            activeColor = primaryColor,
            inactiveColor = inactiveColor
        )
    }
}

@Composable
private fun NavigationItem(
    label: String,
    selected: Boolean,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    activeColor: Color,
    inactiveColor: Color
) {
    val itemColor = if (selected) activeColor else inactiveColor

    Column(
        modifier = Modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.runtime.CompositionLocalProvider(
                androidx.compose.material3.LocalContentColor provides itemColor
            ) {
                icon()
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            color = itemColor,
            fontSize = 10.sp
        )
    }
}
