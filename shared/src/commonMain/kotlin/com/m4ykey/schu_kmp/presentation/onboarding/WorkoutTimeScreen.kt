package com.m4ykey.schu_kmp.presentation.onboarding

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
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
import com.m4ykey.schu_kmp.domain.ActivityLevels
import com.m4ykey.schu_kmp.domain.WorkoutTime
import com.m4ykey.schu_kmp.domain.icon
import com.m4ykey.schu_kmp.domain.subText
import com.m4ykey.schu_kmp.domain.text
import com.m4ykey.schu_kmp.presentation.onboarding.component.OnBoardingSelectionLayout
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.how_long_train
import schukmp.shared.generated.resources.select_preferred_session_duration
import schukmp.shared.generated.resources.train_time_required

@Composable
fun WorkoutTimeScreen(
    onBack : () -> Unit,
    activities : List<ActivityLevels>
) {
    Scaffold(
        topBar = {
            TopBar(onBack = onBack)
        }
    ) { paddingValues ->
        WorkoutTimeContent(
            modifier = Modifier.padding(paddingValues),
            onBack = onBack,
            activities = activities
        )
    }
}

@Composable
fun WorkoutTimeContent(
    modifier : Modifier = Modifier,
    onBack : () -> Unit,
    activities : List<ActivityLevels>
) {
    val currentStep = 4
    val totalSteps = 5

    var selectedTimeError by remember {
        mutableStateOf(false)
    }

    var selectedTime by remember {
        mutableStateOf<WorkoutTime?>(null)
    }

    val times = listOf(
        WorkoutTime.FAST_EFFECTIVE,
        WorkoutTime.SUSTAINABLE,
        WorkoutTime.INTENSELY
    )

    OnBoardingSelectionLayout(
        currentStep = currentStep,
        totalSteps = totalSteps,
        onBack = onBack,
        modifier = modifier,
        title = stringResource(Res.string.how_long_train),
        description = stringResource(Res.string.select_preferred_session_duration),
        previousStepContent = {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(activities) { activity ->
                    PrevStepItem(
                        icon = activity.icon,
                        text = stringResource(activity.text)
                    )
                }
            }
        },
        optionsContent = {
            items(times) { time ->
                WorkoutItem(
                    onSelected = { selected ->
                        if (selected) {
                            selectedTime = time
                            selectedTimeError = false
                        }
                    },
                    icon = time.icon,
                    text = time.text,
                    subText = time.subText,
                    selected = time == selectedTime
                )
            }
        },
        errorMessage = if (selectedTimeError) {
            stringResource(Res.string.train_time_required)
        } else {
            null
        },
        onNext = {
            if (selectedTime == null) {
                selectedTimeError = true
            } else {

            }
        }
    )
}

@Composable
fun WorkoutItem(
    selected : Boolean,
    modifier : Modifier = Modifier,
    text : StringResource,
    subText : StringResource,
    icon : DrawableResource,
    onSelected : (Boolean) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
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
                if (selected) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF81F3E5)
                        )
                    ) {
                        Text(
                            text = stringResource(subText),
                            fontSize = 14.sp,
                            color = Color(0xFF006F66),
                            modifier = Modifier.padding(
                                horizontal = 5.dp,
                                vertical = 2.dp
                            )
                        )
                    }
                } else {
                    Text(
                        text = stringResource(subText),
                        fontSize = 14.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(
                selected = selected,
                onClick = { onSelected(!selected) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF006B5F),
                    unselectedColor = Color(0xFF9E9E9E)
                )
            )
        }
    }
}