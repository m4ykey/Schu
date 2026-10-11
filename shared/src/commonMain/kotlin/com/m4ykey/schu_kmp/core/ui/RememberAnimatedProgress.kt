package com.m4ykey.schu_kmp.core.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberAnimatedProgress(
    currentStep : Int,
    totalSteps : Int
) : Float {
    var animationStarted by remember { mutableStateOf(false) }

    val progress by animateFloatAsState(
        targetValue = if (animationStarted) {
            currentStep.toFloat() / totalSteps
        } else {
            (currentStep - 1).toFloat() / totalSteps
        },
        animationSpec = tween(600),
        label = "onboardingProgress"
    )

    LaunchedEffect(Unit) {
        animationStarted = true
    }

    return progress
}