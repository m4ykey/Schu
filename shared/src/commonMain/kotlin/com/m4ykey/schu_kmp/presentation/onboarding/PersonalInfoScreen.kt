package com.m4ykey.schu_kmp.presentation.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4ykey.schu_kmp.presentation.onboarding.validation.PersonalInfoError
import com.m4ykey.schu_kmp.presentation.onboarding.validation.message
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.age
import schukmp.shared.generated.resources.age_placeholder
import schukmp.shared.generated.resources.back
import schukmp.shared.generated.resources.height
import schukmp.shared.generated.resources.height_placeholder
import schukmp.shared.generated.resources.help_us_tailor_your_plan
import schukmp.shared.generated.resources.ic_age
import schukmp.shared.generated.resources.ic_arrow_back
import schukmp.shared.generated.resources.ic_height
import schukmp.shared.generated.resources.ic_weight
import schukmp.shared.generated.resources.lets_get_to_know_you
import schukmp.shared.generated.resources.next
import schukmp.shared.generated.resources.weight
import schukmp.shared.generated.resources.weight_placeholder

@Composable
fun PersonalInfoScreen(
    modifier : Modifier = Modifier,
    onBack : () -> Unit,
    onNext : () -> Unit
) {
    Scaffold(
        topBar = {
            PersonalTopBar(onBack = onBack)
        }
    ) { paddingValues ->
        PersonalContent(
            onNext = onNext,
            onBack = onBack,
            modifier = modifier.padding(paddingValues)
        )
    }
}

@Composable
fun PersonalContent(
    modifier : Modifier = Modifier,
    onNext : () -> Unit,
    onBack : () -> Unit
) {
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    var ageError by remember {
        mutableStateOf<PersonalInfoError?>(null)
    }

    var heightError by remember {
        mutableStateOf<PersonalInfoError?>(null)
    }

    var weightError by remember {
        mutableStateOf<PersonalInfoError?>(null)
    }

    fun validate() : Boolean {
        ageError = when {
            age.isBlank() ->
                PersonalInfoError.REQUIRED

            age.toIntOrNull() !in 13..100 ->
                PersonalInfoError.INVALID_VALUE

            else ->
                null
        }

        heightError = when {
            height.isBlank() ->
                PersonalInfoError.REQUIRED

            height.toIntOrNull() !in 100..250 ->
                PersonalInfoError.INVALID_VALUE

            else ->
                null
        }

        weightError = when {
            weight.isBlank() ->
                PersonalInfoError.REQUIRED

            weight.replace(',', '.').toDoubleOrNull() == null ->
                PersonalInfoError.INVALID_VALUE

            weight.replace(',', '.').toDouble() !in 30.0..300.0 ->
                PersonalInfoError.INVALID_VALUE

            else -> null
        }

        return ageError == null &&
                weightError == null &&
                heightError == null
    }

    val currentStep = 1
    val totalSteps = 5

    var animationStarted by remember { mutableStateOf(false) }

    val progress by animateFloatAsState(
        targetValue = if (animationStarted) {
            currentStep.toFloat() / totalSteps
        } else {
            0f
        },
        label = "onboardingProgress",
        animationSpec = tween(durationMillis = 600)
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
                .fillMaxWidth()
                .height(6.dp),
            trackColor = Color(0xFF7E8E9),
            color = Color(0xFF006B5F)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            ),
            text = stringResource(Res.string.lets_get_to_know_you)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            fontSize = 16.sp,
            text = stringResource(Res.string.help_us_tailor_your_plan)
        )
        Spacer(modifier = Modifier.height(20.dp))
        InformationItem(
            text = Res.string.age,
            value = age,
            onValueChange = {
                if (it.all(Char::isDigit)) {
                    age = it
                    ageError = null
                }
            },
            icon = Res.drawable.ic_age,
            placeholder = Res.string.age_placeholder,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            error = ageError
        )
        InformationItem(
            text = Res.string.height,
            value = height,
            onValueChange = {
                if (it.all(Char::isDigit)) {
                    height = it
                    heightError = null
                }
            },
            icon = Res.drawable.ic_height,
            placeholder = Res.string.height_placeholder,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            error = heightError
        )
        InformationItem(
            text = Res.string.weight,
            value = weight,
            onValueChange = {
                if (it.matches(Regex("""\d*([.,]\d*)?"""))) {
                    weight = it
                    weightError = null
                }
            },
            icon = Res.drawable.ic_weight,
            placeholder = Res.string.weight_placeholder,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            error = weightError
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onBack,
                modifier = Modifier.height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE7E8E9))
            ) {
                Text(
                    color = Color.Black,
                    text = stringResource(Res.string.back)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    if (validate()) {
                        onNext()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006B5F)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
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
fun PersonalTopBar(onBack : () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "",

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

@Composable
fun InformationItem(
    text : StringResource,
    value : String,
    onValueChange : (String) -> Unit,
    icon : DrawableResource,
    placeholder : StringResource,
    keyboardOptions: KeyboardOptions,
    error : PersonalInfoError? = null
) {
    Text(
        fontSize = 16.sp,
        text = stringResource(text)
    )
    Spacer(modifier = Modifier.height(10.dp))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = if (error == null) {
                    20.dp
                } else {
                    8.dp
                }
            ),
        leadingIcon = {
            Icon(
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                painter = painterResource(icon)
            )
        },
        keyboardOptions = keyboardOptions,
        isError = error != null,
        placeholder = {
            Text(text = stringResource(placeholder))
        },
        supportingText = {
            error?.let {
                Text(text = stringResource(it.message))
            }
        }
    )
}