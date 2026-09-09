package com.m4ykey.schu_kmp.presentation.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnBoardingViewModel : ViewModel() {

    private val _state = MutableStateFlow(PersonalInfoState())
    val state = _state.asStateFlow()

}