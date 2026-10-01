package com.m4ykey.schu_kmp.domain

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.extremely_active
import schukmp.shared.generated.resources.extremely_active_sub_text
import schukmp.shared.generated.resources.ic_couch
import schukmp.shared.generated.resources.ic_dumbell
import schukmp.shared.generated.resources.ic_endurance
import schukmp.shared.generated.resources.ic_extreme_activity
import schukmp.shared.generated.resources.ic_walk
import schukmp.shared.generated.resources.lightly_active
import schukmp.shared.generated.resources.lightly_active_sub_text
import schukmp.shared.generated.resources.moderately_active
import schukmp.shared.generated.resources.moderately_active_sub_text
import schukmp.shared.generated.resources.sedentary
import schukmp.shared.generated.resources.sedentary_sub_text
import schukmp.shared.generated.resources.very_active
import schukmp.shared.generated.resources.very_active_sub_text

enum class ActivityLevels {
    SEDENTARY,
    LIGHTLY_ACTIVE,
    MODERATELY_ACTIVE,
    VERY_ACTIVE,
    EXTREMELY_ACTIVE
}

val ActivityLevels.icon: DrawableResource
    get() = when (this) {
        ActivityLevels.SEDENTARY -> Res.drawable.ic_couch
        ActivityLevels.LIGHTLY_ACTIVE -> Res.drawable.ic_walk
        ActivityLevels.MODERATELY_ACTIVE -> Res.drawable.ic_endurance
        ActivityLevels.VERY_ACTIVE -> Res.drawable.ic_dumbell
        ActivityLevels.EXTREMELY_ACTIVE -> Res.drawable.ic_extreme_activity
    }

val ActivityLevels.text: StringResource
    get() = when (this) {
        ActivityLevels.SEDENTARY -> Res.string.sedentary
        ActivityLevels.LIGHTLY_ACTIVE -> Res.string.lightly_active
        ActivityLevels.MODERATELY_ACTIVE -> Res.string.moderately_active
        ActivityLevels.VERY_ACTIVE -> Res.string.very_active
        ActivityLevels.EXTREMELY_ACTIVE -> Res.string.extremely_active
    }

val ActivityLevels.subText: StringResource
    get() = when (this) {
        ActivityLevels.SEDENTARY -> Res.string.sedentary_sub_text
        ActivityLevels.LIGHTLY_ACTIVE -> Res.string.lightly_active_sub_text
        ActivityLevels.MODERATELY_ACTIVE -> Res.string.moderately_active_sub_text
        ActivityLevels.VERY_ACTIVE -> Res.string.very_active_sub_text
        ActivityLevels.EXTREMELY_ACTIVE -> Res.string.extremely_active_sub_text
    }