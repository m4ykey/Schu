package com.m4ykey.schu_kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.m4ykey.schu_kmp.presentation.onboarding.PersonalInfoScreen
import com.m4ykey.schu_kmp.presentation.welcome.WelcomeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        PersonalInfoScreen(
            onBack = {},
            onNext = {}
        )
    }
}