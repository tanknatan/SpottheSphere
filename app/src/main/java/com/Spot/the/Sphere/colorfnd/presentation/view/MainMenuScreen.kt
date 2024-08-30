package com.Spot.the.Sphere.colorfnd.presentation.view

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Color.Sphere.Challenge.gamecolor.data.SoundManager

import com.Spot.the.Sphere.colorfnd.R
import com.Spot.the.Sphere.colorfnd.presentation.navigation.OutlinedText

@Composable
fun MainMenuScreen(
    onStartClicked: () -> Unit,
    onPolicyClicked: () -> Unit,
    onOptionClicked: () -> Unit
) {
    val activity = LocalContext.current as? Activity
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = R.drawable.background), // Замените на ваше фоновое изображение
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Кнопка "Start"
            Box(
                modifier = Modifier

                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rec), // Замените на ваш ресурс для кнопки
                    contentDescription = null,

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            SoundManager.playSound()
                            onStartClicked() }
                )
                OutlinedText(
                    text = "Start",
                    outlineColor = Color.Black,
                    fillColor = Color.White,
                    fontSize = 50.sp
                )
            }

            // Кнопка "Options"
            Box(
                modifier = Modifier

                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rec), // Замените на ваш ресурс для кнопки
                    contentDescription = null,

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            SoundManager.playSound()
                            onOptionClicked() }
                )
                OutlinedText(
                    text = "Options",
                    outlineColor = Color.Black,
                    fillColor = Color.White,
                    fontSize = 50.sp
                )
            }

            // Кнопка "Policy"
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rec), // Замените на ваш ресурс для кнопки
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            SoundManager.playSound()
                            onPolicyClicked() }
                )
                OutlinedText(
                    text = "Policy",
                    outlineColor = Color.Black,
                    fillColor = Color.White,
                    fontSize = 50.sp
                )
            }
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rec), // Замените на ваш ресурс для кнопки
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            SoundManager.playSound()
                            activity?.finish() }
                )
                OutlinedText(
                    text = "Exit",
                    outlineColor = Color.Black,
                    fillColor = Color.White,
                    fontSize = 50.sp
                )
            }
        }
    }
}