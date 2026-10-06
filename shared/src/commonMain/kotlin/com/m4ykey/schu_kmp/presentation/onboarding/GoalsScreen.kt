package com.m4ykey.schu_kmp.presentation.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.m4ykey.schu_kmp.domain.FitnessGoal
import com.m4ykey.schu_kmp.domain.icon
import com.m4ykey.schu_kmp.domain.title
import com.m4ykey.schu_kmp.presentation.onboarding.model.PersonalInfoItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.back
import schukmp.shared.generated.resources.cm
import schukmp.shared.generated.resources.goal_required
import schukmp.shared.generated.resources.ic_age
import schukmp.shared.generated.resources.ic_arrow_back
import schukmp.shared.generated.resources.ic_height
import schukmp.shared.generated.resources.ic_weight
import schukmp.shared.generated.resources.kg
import schukmp.shared.generated.resources.next
import schukmp.shared.generated.resources.prev_step
import schukmp.shared.generated.resources.this_will_help_personalize_your_training_plan
import schukmp.shared.generated.resources.what_is_your_goal
import schukmp.shared.generated.resources.years
import kotlin.collections.emptySet

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
            GoalsTopBar(onBack = onBack)
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

    var animationStarted by remember { mutableStateOf(false) }

    val progress by animateFloatAsState(
        label = "onboardingProgress",
        animationSpec = tween(durationMillis = 600),
        targetValue = if (animationStarted) {
            currentStep.toFloat() / totalSteps
        } else {
            (currentStep - 1).toFloat() / totalSteps
        }
    )

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

    LaunchedEffect(Unit) {
        animationStarted = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .height(6.dp)
                .fillMaxWidth(),
            color = Color(0xFF006B5F),
            trackColor = Color(0xFFE7E8E9)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                Text(
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium,
                    text = stringResource(Res.string.what_is_your_goal)
                )
            }
            item {
                Text(
                    fontSize = 16.sp,
                    text = stringResource(Res.string.this_will_help_personalize_your_training_plan)
                )
            }
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
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        if (goalsError) {
            Text(
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                color = MaterialTheme.colorScheme.error,
                text = stringResource(Res.string.goal_required)
            )
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(Res.string.prev_step),
            color = Color.DarkGray,
            fontSize = 16.sp
        )
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
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE7E8E9)),
                modifier = Modifier.height(48.dp)
            ) {
                Text(
                    color = Color.Black,
                    text = stringResource(Res.string.back)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    if (selectedGoals.isEmpty()) {
                        goalsError = true
                    } else {
                        onNext(selectedGoals.toList())
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006B5F))
            ) {
                Text(
                    color = Color.White,
                    text = stringResource(Res.string.next)
                )
            }
        }
    }
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

@Composable
fun GoalsTopBar(onBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = ""
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    contentDescription = stringResource(Res.string.back),
                    painter = painterResource(Res.drawable.ic_arrow_back)
                )
            }
        }
    )
}