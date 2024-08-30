package com.Spot.the.Sphere.colorfnd.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Spot.the.Sphere.colorfnd.R
import com.Spot.the.Sphere.colorfnd.presentation.navigation.OutlinedText

import kotlinx.coroutines.delay


@Composable
fun SplashScreen(onTimeout: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(1000) // Short delay before transitioning
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop, // To make the image fill the entire screen
            modifier = Modifier.fillMaxSize()
        )

        // Column removed from center and placed at bottom start
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Add padding if necessary
            contentAlignment = Alignment.BottomStart
        ) {
            OutlinedText(
                text = "Loading...",
                outlineColor = Color.Black,
                fillColor = Color.White,
                fontSize = 50.sp
            )
        }
    }
}

