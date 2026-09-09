package com.m4ykey.schu_kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.m4ykey.schu_kmp.presentation.onboarding.GoalsScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        GoalsScreen(
            onBack = {},
            onNext = {}
        )
    }
}