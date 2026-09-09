package com.m4ykey.schu_kmp.presentation.onboarding.validation

import org.jetbrains.compose.resources.StringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.field_required
import schukmp.shared.generated.resources.invalid_value

enum class PersonalInfoError {
    REQUIRED,
    INVALID_VALUE
}

val PersonalInfoError.message : StringResource
    get() = when (this) {
        PersonalInfoError.REQUIRED -> Res.string.field_required
        PersonalInfoError.INVALID_VALUE -> Res.string.invalid_value
    }