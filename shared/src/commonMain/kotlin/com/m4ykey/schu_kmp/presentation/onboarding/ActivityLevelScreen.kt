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
import androidx.compose.foundation.lazy.LazyRow
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
import com.m4ykey.schu_kmp.domain.ActivityLevels
import com.m4ykey.schu_kmp.domain.FitnessGoal
import com.m4ykey.schu_kmp.domain.icon
import com.m4ykey.schu_kmp.domain.subText
import com.m4ykey.schu_kmp.domain.text
import com.m4ykey.schu_kmp.domain.title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.activity_level_sub_text
import schukmp.shared.generated.resources.back
import schukmp.shared.generated.resources.ic_arrow_back
import schukmp.shared.generated.resources.next
import schukmp.shared.generated.resources.prev_step
import schukmp.shared.generated.resources.what_is_your_activity_level

@Composable
fun ActivityLevelScreen(
    modifier: Modifier = Modifier,
    onBack : () -> Unit,
    onNext : () -> Unit,
    goals : List<FitnessGoal>
) {
    Scaffold(
        topBar = {
            ActivityLevelTopBar(onBack = onBack)
        }
    ) { paddingValues ->
        ActivityLevelContent(
            modifier = modifier.padding(paddingValues),
            onBack = onBack,
            onNext = onNext,
            goals = goals
        )
    }
}

@Composable
fun ActivityLevelContent(
    modifier: Modifier = Modifier,
    onNext: () -> Unit,
    onBack: () -> Unit,
    goals : List<FitnessGoal>
) {
    val currentStep = 3
    val totalSteps = 5

    var animationStarted by remember { mutableStateOf(false) }

    val progress by animateFloatAsState(
        targetValue = if (animationStarted) {
            currentStep.toFloat() / totalSteps
        } else {
            (currentStep - 1).toFloat() / totalSteps
        },
        animationSpec = tween(durationMillis = 600),
        label = "onboardingProgress"
    )

    LaunchedEffect(Unit) {
        animationStarted = true
    }

    var selectedLevels by remember {
        mutableStateOf<Set<ActivityLevels>>(emptySet())
    }

    val levels = listOf(
        ActivityLevels.SEDENTARY,
        ActivityLevels.LIGHTLY_ACTIVE,
        ActivityLevels.MODERATELY_ACTIVE,
        ActivityLevels.VERY_ACTIVE,
        ActivityLevels.EXTREMELY_ACTIVE
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
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
                    text = stringResource(Res.string.what_is_your_activity_level)
                )
            }
            item {
                Text(
                    fontSize = 16.sp,
                    text = stringResource(Res.string.activity_level_sub_text)
                )
            }
            items(levels) { level ->
                ActivityLevelItem(
                    selected = level in selectedLevels,
                    text = level.text,
                    subText = level.subText,
                    icon = level.icon,
                    onSelected = { selected ->
                        selectedLevels = if (selected) {
                            selectedLevels + level
                        } else {
                            selectedLevels - level
                        }

                        if (selected) {

                        }
                    }
                )
            }
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(Res.string.prev_step),
            color = Color.DarkGray,
            fontSize = 16.sp
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(goals) { goal ->
                PrevStepItem(
                    icon = goal.icon,
                    text = stringResource(goal.title)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE7E8E9)),
                modifier = Modifier.height(48.dp)
            ) {
                Text(
                    text = stringResource(Res.string.back),
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    if (selectedLevels.isEmpty()) {

                    } else {
                        onNext()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006B5F)),
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(Res.string.next),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ActivityLevelItem(
    modifier: Modifier = Modifier,
    text : StringResource,
    subText : StringResource,
    icon : DrawableResource,
    selected : Boolean,
    onSelected : (Boolean) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (selected) 4.dp else 1.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) {
                    Color(0xFF006B5F)
                } else {
                    Color(0xFFE0E0E0)
                },
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF81F3E5)
                ),
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        contentDescription = null,
                        painter = painterResource(icon),
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF006F66),
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(text),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = stringResource(subText),
                    fontSize = 14.sp
                )
            }
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
fun ActivityLevelTopBar(onBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "",
                fontSize = 18.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_back),
                    contentDescription = stringResource(Res.string.back)
                )
            }
        }
    )
}