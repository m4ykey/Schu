package com.m4ykey.schu_kmp.presentation.onboarding.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4ykey.schu_kmp.core.ui.rememberAnimatedProgress
import org.jetbrains.compose.resources.stringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.back
import schukmp.shared.generated.resources.next
import schukmp.shared.generated.resources.prev_step

@Composable
fun OnBoardingSelectionLayout(
    modifier : Modifier = Modifier,
    currentStep : Int,
    totalSteps : Int,
    onBack: () -> Unit,
    onNext : () -> Unit,
    title : String,
    description : String,
    optionsContent : LazyListScope.() -> Unit,
    errorMessage : String?,
    previousStepContent : @Composable () -> Unit
) {
    val progress = rememberAnimatedProgress(
        totalSteps = totalSteps,
        currentStep = currentStep
    )

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
                    text = title
                )
            }
            item {
                Text(
                    fontSize = 16.sp,
                    text = description
                )
            }

            optionsContent()

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        if (errorMessage != null) {
            Text(
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                color = MaterialTheme.colorScheme.error,
                text = errorMessage
            )
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(Res.string.prev_step),
            color = Color.DarkGray,
            fontSize = 16.sp
        )

        previousStepContent()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
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
                onClick = onNext,
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