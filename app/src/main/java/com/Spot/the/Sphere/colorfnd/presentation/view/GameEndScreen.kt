package com.Spot.the.Sphere.colorfnd.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Color.Sphere.Challenge.gamecolor.data.SoundManager
import com.Spot.the.Sphere.colorfnd.R
import com.Spot.the.Sphere.colorfnd.presentation.navigation.OutlinedText

@Composable
fun GameEndScreen(isWin: Boolean, score: Int, onLevelSelect: () -> Unit, onNextLevel: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background), // Замените на ваше фоновое изображение
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            // Текст "You win" или "You lose"
            OutlinedText(
                text = if (isWin) {
                    "You win"
                } else {
                    "You lose"
                },
                outlineColor = Color.Black,
                fillColor = Color.White,
                fontSize = 35.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Панель с очками
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_rec),  // Используем main_rec.xml
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Score: $score",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка "Next"
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .size(150.dp, 50.dp)
                    .clickable {
                        SoundManager.playSound()
                        onNextLevel() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rec),  // Используем ok_rec.xml
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "Next",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }

        // Кнопка меню (иконка) в правом верхнем углу
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(70.dp)
                .clickable {
                    SoundManager.playSound()
                    onLevelSelect() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),  // Замените на ваш ресурс меню
                contentDescription = "Menu"
            )
        }
    }
}
