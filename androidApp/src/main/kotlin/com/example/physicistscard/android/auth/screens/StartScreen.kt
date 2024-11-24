package com.example.physicistscard.android.auth.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.physicistscard.android.auth.components.AppLogo
import com.example.physicistscard.android.navigation.whole.BottomNavItem
import kotlinx.coroutines.delay

@Composable
fun StartScreen(navController: NavController) {
    var logoVisible by remember { mutableStateOf(false) }
    var buttonsVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(visible = logoVisible, enter = androidx.compose.animation.fadeIn(animationSpec = tween(1000))) {
            AppLogo()
        }
        Spacer(modifier = Modifier.height(120.dp))
        AnimatedVisibility(visible = buttonsVisible, enter = androidx.compose.animation.fadeIn(animationSpec = tween(800))) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularButton(icon = Icons.Filled.Person, description = "登录", onClick = {
                        navController.navigate("login")
                    })
                    CircularButton(icon = Icons.Filled.Lock, description = "随便看看", onClick = {
                        navController.navigate(BottomNavItem.Collection.route)
                    })
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularButton(icon = Icons.Filled.Phone, description = "手机号码", onClick = {
                        navController.navigate("phone_register")
                    })
                    CircularButton(icon = Icons.Filled.Email, description = "邮箱", onClick = {
                        navController.navigate("email_register")
                    })
                }
                Spacer(modifier = Modifier.height(150.dp))
                Text(
                    text = "选择您的方式进行登入",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(300)
        logoVisible = true
        delay(1000)
        buttonsVisible = true
    }
}

@Composable
fun CircularButton(icon: ImageVector, description: String, onClick: () -> Unit) {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val transition = updateTransition(targetState = buttonState, label = "Button Transition")

    val scale: Float by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 300) },
        label = "Scale Animation"
    ) { state ->
        when (state) {
            ButtonState.Pressed -> 0.9f
            ButtonState.Idle -> 1f
        }
    }

    val backgroundColor: Color by transition.animateColor(
        transitionSpec = { tween(durationMillis = 300) },
        label = "Color Animation"
    ) { state ->
        when (state) {
            ButtonState.Pressed -> MaterialTheme.colorScheme.primaryContainer
            ButtonState.Idle -> MaterialTheme.colorScheme.primary
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = {
                buttonState = ButtonState.Pressed
                onClick()
                buttonState = ButtonState.Idle
            },
            modifier = Modifier
                .size(80.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .background(color = backgroundColor, shape = CircleShape)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = description,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(36.dp)
            )
        }
        Text(
            text = description,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

enum class ButtonState {
    Idle,
    Pressed
}