package com.m4ykey.schu_kmp.domain

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.endurance
import schukmp.shared.generated.resources.general_fitness
import schukmp.shared.generated.resources.ic_dumbell
import schukmp.shared.generated.resources.ic_endurance
import schukmp.shared.generated.resources.ic_fitness
import schukmp.shared.generated.resources.ic_loss_weight
import schukmp.shared.generated.resources.ic_muscle
import schukmp.shared.generated.resources.increase_strength
import schukmp.shared.generated.resources.muscle_gain
import schukmp.shared.generated.resources.weight_loss

enum class FitnessGoal {
    WEIGHT_LOSS,
    STRENGTH,
    MUSCLE_GAIN,
    ENDURANCE,
    GENERAL_FITNESS
}

val FitnessGoal.icon : DrawableResource
    get() = when (this) {
        FitnessGoal.WEIGHT_LOSS -> Res.drawable.ic_loss_weight
        FitnessGoal.STRENGTH -> Res.drawable.ic_dumbell
        FitnessGoal.MUSCLE_GAIN -> Res.drawable.ic_muscle
        FitnessGoal.ENDURANCE -> Res.drawable.ic_endurance
        FitnessGoal.GENERAL_FITNESS -> Res.drawable.ic_fitness
    }

val FitnessGoal.title : StringResource
    get() = when (this) {
        FitnessGoal.WEIGHT_LOSS -> Res.string.weight_loss
        FitnessGoal.STRENGTH -> Res.string.increase_strength
        FitnessGoal.MUSCLE_GAIN -> Res.string.muscle_gain
        FitnessGoal.ENDURANCE -> Res.string.endurance
        FitnessGoal.GENERAL_FITNESS -> Res.string.general_fitness
    }