package com.m4ykey.schu_kmp.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4ykey.schu_kmp.core.ui.TopBar
import com.m4ykey.schu_kmp.core.ui.rememberAnimatedProgress
import org.jetbrains.compose.resources.stringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.how_long_train
import schukmp.shared.generated.resources.select_preferred_session_duration

@Composable
fun WorkoutTimeScreen(
    onBack : () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(onBack = onBack)
        }
    ) { paddingValues ->
        WorkoutTimeContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun WorkoutTimeContent(
    modifier : Modifier = Modifier
) {
    val currentStep = 4
    val totalSteps = 5

    val progress = rememberAnimatedProgress(
        totalSteps = totalSteps,
        currentStep = currentStep
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = Color(0xFF006B5F),
            trackColor = Color(0xFFE7E8E9)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                Text(
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium,
                    text = stringResource(Res.string.how_long_train)
                )
            }
            item {
                Text(
                    fontSize = 16.sp,
                    text = stringResource(Res.string.select_preferred_session_duration)
                )
            }
        }
    }
}