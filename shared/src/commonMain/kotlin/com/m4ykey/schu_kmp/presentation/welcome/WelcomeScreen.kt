package com.m4ykey.schu_kmp.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import schukmp.shared.generated.resources.Res
import schukmp.shared.generated.resources.app_name
import schukmp.shared.generated.resources.get_started
import schukmp.shared.generated.resources.ic_arrow_forward
import schukmp.shared.generated.resources.ic_workout_1
import schukmp.shared.generated.resources.welcome_text
import schukmp.shared.generated.resources.welcome_text_content

@Composable
fun WelcomeScreen(
    modifier : Modifier = Modifier,
    onGetStarted : () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            WelcomeTopBar()
        }
    ) { padding ->
        WelcomeContent(
            modifier = modifier
                .padding(padding)
                .padding(16.dp),
            onGetStarted = onGetStarted
        )
    }
}

@Composable
fun WelcomeTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                text = stringResource(Res.string.app_name)
            )
        }
    )
}

@Composable
fun WelcomeContent(
    modifier: Modifier = Modifier,
    onGetStarted: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Image(
                modifier = modifier
                    .size(200.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                painter = painterResource(Res.drawable.ic_workout_1)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(Res.string.welcome_text_content),
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(horizontal = 26.dp),
            text = stringResource(Res.string.welcome_text),
            style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onGetStarted,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF006B5F)
            ),
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
        ) {
            Text(
                fontSize = 16.sp,
                color = Color.White,
                text = stringResource(Res.string.get_started)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                tint = Color.White,
                contentDescription = null,
                painter = painterResource(Res.drawable.ic_arrow_forward)
            )
        }
    }
}