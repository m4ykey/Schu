package com.m4ykey.schu_kmp.presentation.onboarding

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4ykey.schu_kmp.core.ui.TopBar
import com.m4ykey.schu_kmp.domain.FitnessGoal
import com.m4ykey.schu_kmp.domain.icon
import com.m4ykey.schu_kmp.domain.title
import com.m4ykey.schu_kmp.presentation.onboarding.component.OnBoardingSelectionLayout
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.cm
import schukmp.shared.generated.resources.goal_required
import schukmp.shared.generated.resources.ic_age
import schukmp.shared.generated.resources.ic_height
import schukmp.shared.generated.resources.ic_weight
import schukmp.shared.generated.resources.kg
import schukmp.shared.generated.resources.this_will_help_personalize_your_training_plan
import schukmp.shared.generated.resources.what_is_your_goal
import schukmp.shared.generated.resources.years

@Composable
fun GoalsScreen(
    modifier : Modifier = Modifier,
    onNext : (goals : List<FitnessGoal>) -> Unit,
    onBack : () -> Unit,
    age : Int,
    height : Int,
    weight : Double
) {
    Scaffold(
        topBar = {
            TopBar(onBack = onBack)
        }
    ) { paddingValues ->
        GoalsContent(
            onNext = onNext,
            onBack = onBack,
            modifier = modifier.padding(paddingValues),
            age = age,
            weight = weight,
            height = height
        )
    }
}

@Composable
fun GoalsContent(
    modifier : Modifier = Modifier,
    onNext: (goals : List<FitnessGoal>) -> Unit,
    onBack: () -> Unit,
    age : Int,
    weight : Double,
    height : Int
) {
    val currentStep = 2
    val totalSteps = 5

    var selectedGoals by remember {
        mutableStateOf<Set<FitnessGoal>>(emptySet())
    }

    var goalsError by remember {
        mutableStateOf(false)
    }

    val goals = listOf(
        FitnessGoal.WEIGHT_LOSS,
        FitnessGoal.STRENGTH,
        FitnessGoal.MUSCLE_GAIN,
        FitnessGoal.ENDURANCE,
        FitnessGoal.GENERAL_FITNESS
    )

    OnBoardingSelectionLayout(
        modifier = modifier,
        currentStep = currentStep,
        title = stringResource(Res.string.what_is_your_goal),
        description = stringResource(Res.string.this_will_help_personalize_your_training_plan),
        onBack = onBack,
        errorMessage = if (goalsError) {
            stringResource(Res.string.goal_required)
        } else {
            null
        },
        onNext = {
            if (selectedGoals.isEmpty()) {
                goalsError = true
            } else {
                onNext(selectedGoals.toList())
            }
        },
        totalSteps = totalSteps,
        optionsContent = {
            items(goals) { goal ->
                GoalItem(
                    text = goal.title,
                    icon = goal.icon,
                    selected = goal in selectedGoals,
                    onSelected = { selected ->
                        selectedGoals = if (selected) {
                            selectedGoals + goal
                        } else {
                            selectedGoals - goal
                        }

                        if (selected) {
                            goalsError = false
                        }
                    }
                )
            }
        },
        previousStepContent = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                PrevStepItem(
                    text = age.toString() + " " + stringResource(Res.string.years),
                    icon = Res.drawable.ic_age
                )
                PrevStepItem(
                    text = height.toString() + " " + stringResource(Res.string.cm),
                    icon = Res.drawable.ic_height
                )
                PrevStepItem(
                    text = weight.toString() + " " + stringResource(Res.string.kg),
                    icon = Res.drawable.ic_weight
                )
            }
        }
    )
}

@Composable
fun GoalItem(
    modifier : Modifier = Modifier,
    text : StringResource,
    icon : DrawableResource,
    selected : Boolean,
    onSelected : (Boolean) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                shape = RoundedCornerShape(16.dp),
                elevation = if (selected) 4.dp else 1.dp
            )
            .border(
                shape = RoundedCornerShape(16.dp),
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) {
                    Color(0xFF006B5F)
                } else {
                    Color(0xFFE0E0E0)
                }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Color(0xFF81F3E5)),
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        contentDescription = null,
                        tint = Color(0xFF006F66),
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(icon)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(text),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = selected,
                onCheckedChange = onSelected,
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.White,
                    checkedColor = Color(0xFF006B5F),
                    uncheckedColor = Color(0xFF9E9E9E)
                )
            )
        }
    }
}

@Composable
fun PrevStepItem(
    modifier : Modifier = Modifier,
    icon : DrawableResource,
    text : String
) {
    SuggestionChip(
        onClick = {},
        label = {
            Text(text = text)
        },
        icon = {
            Icon(
                tint = Color.Gray,
                modifier = modifier.size(16.dp),
                painter = painterResource(icon),
                contentDescription = null
            )
        }
    )
}