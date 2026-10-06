package com.m4ykey.schu_kmp.navigation

import androidx.navigation3.runtime.NavKey
import com.m4ykey.schu_kmp.domain.FitnessGoal
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object WelcomeScreen : Route

    @Serializable
    data object PersonalInfoScreen : Route

    @Serializable
    data class GoalsScreen(val age : Int, val weight : Double, val height : Int) : Route

    @Serializable
    data class ActivityLevelScreen(val goals : List<FitnessGoal>) : Route

}