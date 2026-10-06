package com.m4ykey.schu_kmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.m4ykey.schu_kmp.navigation.AppNavigation

@Composable
@Preview
fun App() {
    MaterialTheme {
        AppNavigation(modifier = Modifier.fillMaxSize())
    }
}