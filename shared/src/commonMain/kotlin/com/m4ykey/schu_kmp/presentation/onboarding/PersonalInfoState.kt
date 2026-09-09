package com.m4ykey.schu_kmp.presentation.onboarding

import com.m4ykey.schu_kmp.presentation.onboarding.validation.PersonalInfoError

data class PersonalInfoState(
    val age : String = "",
    val weight : String = "",
    val height : String = "",
    val ageError : PersonalInfoError? = null,
    val weightError : PersonalInfoError? = null,
    val heightError : PersonalInfoError? = null
)
