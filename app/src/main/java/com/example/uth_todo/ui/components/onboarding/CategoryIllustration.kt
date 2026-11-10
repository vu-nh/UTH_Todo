package com.example.uth_todo.ui.components.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.uth_todo.R

// Onboarding2
@Composable
fun CategoryIllustration() {
    Box(
        modifier = Modifier
            .size(240.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(Color(0x1A2F5BEA)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(112.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = (RoundedCornerShape(56.dp)),
                    spotColor = Color(0x1F141428))
                .background(Color(0xFFFFFFFF)   ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.boarding2
                ),
                contentDescription = "Biểu tượng tốt nghiệp",
                modifier = Modifier.size(52.dp)
            )
        }
    }
}


