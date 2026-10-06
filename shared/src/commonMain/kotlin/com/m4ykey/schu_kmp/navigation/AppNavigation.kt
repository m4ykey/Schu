package com.m4ykey.schu_kmp.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.m4ykey.schu_kmp.presentation.onboarding.ActivityLevelScreen
import com.m4ykey.schu_kmp.presentation.onboarding.GoalsScreen
import com.m4ykey.schu_kmp.presentation.onboarding.PersonalInfoScreen
import com.m4ykey.schu_kmp.presentation.welcome.WelcomeScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Composable
fun AppNavigation(
    modifier : Modifier = Modifier
) {
    val rootBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.WelcomeScreen::class)
                    subclass(Route.PersonalInfoScreen::class)
                    subclass(Route.GoalsScreen::class)
                    subclass(Route.ActivityLevelScreen::class)
                }
            }
        },
        Route.WelcomeScreen
    )

    fun navigateTo(route : Route) {
        if (rootBackStack.lastOrNull() != route) {
            rootBackStack.add(route)
        }
    }

    fun navigateBack() {
        if (rootBackStack.size > 1) {
            rootBackStack.removeAt(rootBackStack.lastIndex)
        }
    }

    NavDisplay(
        backStack = rootBackStack,
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = {
            slideInHorizontally { it } togetherWith
                    slideOutHorizontally { -it }
        },
        popTransitionSpec = {
            slideInHorizontally { -it } togetherWith
                    slideOutHorizontally { it }
        },
        entryProvider = entryProvider {
            entry<Route.WelcomeScreen> {
                WelcomeScreen(
                    onGetStarted = { navigateTo(Route.PersonalInfoScreen) }
                )
            }
            entry<Route.PersonalInfoScreen> {
                PersonalInfoScreen(
                    onBack = ::navigateBack,
                    onNext = { item ->
                        navigateTo(
                            Route.GoalsScreen(
                                age = item.age,
                                weight = item.weight,
                                height = item.height
                            )
                        )
                    }
                )
            }
            entry<Route.GoalsScreen> { key ->
                GoalsScreen(
                    onBack = ::navigateBack,
                    onNext = { goals ->
                        navigateTo(
                            Route.ActivityLevelScreen(goals = goals)
                        )
                    },
                    age = key.age,
                    weight = key.weight,
                    height = key.height
                )
            }
            entry<Route.ActivityLevelScreen> { key ->
                ActivityLevelScreen(
                    onBack = ::navigateBack,
                    onNext = {},
                    goals = key.goals
                )
            }
        }
    )
}