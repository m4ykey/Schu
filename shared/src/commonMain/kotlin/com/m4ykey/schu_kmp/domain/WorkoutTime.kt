package com.m4ykey.schu_kmp.domain

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.fast_effective
import schukmp.shared.generated.resources.fast_effective_time
import schukmp.shared.generated.resources.ic_fire
import schukmp.shared.generated.resources.ic_scale
import schukmp.shared.generated.resources.ic_thunder
import schukmp.shared.generated.resources.intensely
import schukmp.shared.generated.resources.intensely_time
import schukmp.shared.generated.resources.sustainable
import schukmp.shared.generated.resources.sustainable_time

enum class WorkoutTime {
    FAST_EFFECTIVE,
    SUSTAINABLE,
    INTENSELY
}

val WorkoutTime.icon : DrawableResource
    get() = when (this) {
        WorkoutTime.FAST_EFFECTIVE -> Res.drawable.ic_thunder
        WorkoutTime.SUSTAINABLE -> Res.drawable.ic_scale
        WorkoutTime.INTENSELY -> Res.drawable.ic_fire
    }

val WorkoutTime.text : StringResource
    get() = when (this) {
        WorkoutTime.FAST_EFFECTIVE -> Res.string.fast_effective_time
        WorkoutTime.SUSTAINABLE -> Res.string.sustainable_time
        WorkoutTime.INTENSELY -> Res.string.intensely_time
    }

val WorkoutTime.subText : StringResource
    get() = when (this) {
        WorkoutTime.FAST_EFFECTIVE -> Res.string.fast_effective
        WorkoutTime.SUSTAINABLE -> Res.string.sustainable
        WorkoutTime.INTENSELY -> Res.string.intensely
    }