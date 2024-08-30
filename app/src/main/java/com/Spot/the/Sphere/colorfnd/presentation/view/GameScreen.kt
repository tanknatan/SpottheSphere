package com.Spot.the.Sphere.colorfnd.presentation.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Color.Sphere.Challenge.gamecolor.data.Levels
import com.Color.Sphere.Challenge.gamecolor.data.SoundManager
import com.Spot.the.Sphere.colorfnd.R
import com.Spot.the.Sphere.colorfnd.presentation.navigation.OutlinedText
import kotlinx.coroutines.delay

@Composable
fun GameScreen(level: Levels, onLevelSelect: () -> Unit, onBackClicked: () -> Unit) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LevelPreferences", Context.MODE_PRIVATE)
    val levelManager = LevelManager(sharedPreferences)

    var score by remember { mutableStateOf(0) }
    val targetScore =
        level.T * 10 // Устанавливаем целевой счет, например, 10 очков за каждый правильный круг
    var timeRemaining by remember { mutableStateOf(level.time) } // начальное время в секундах
    var circles by remember { mutableStateOf(generateCirclesGrid(level.N, level.M, level.T)) }
    var levelCompleted by remember { mutableStateOf(false) }

    // Таймер для отсчета времени
    LaunchedEffect(timeRemaining) {
        while (timeRemaining > 0 && score < targetScore && !levelCompleted) {
            delay(1000L)
            timeRemaining -= 1
        }
        if (timeRemaining <= 0 || score >= targetScore) {
            levelCompleted = true
            if (score >= targetScore) {
                levelManager.unlockNextLevel(level.ordinal + 1)
            }
        }
    }

    if (levelCompleted) {
        val isWin = score >= targetScore
        GameEndScreen(
            isWin = isWin,
            score = score,
            onLevelSelect = onLevelSelect,
            onNextLevel = onLevelSelect
        )
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            // Фоновое изображение
            Image(
                painter = painterResource(id = R.drawable.bg2),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                // Панель со счетом и временем
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Счет
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.score_rec),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedText(
                            text = "Score: $score",
                            outlineColor = Color.Black,
                            fillColor = Color.White,
                            fontSize = 45.sp
                        )

                    }

                    // Время
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Slider(
                            value = timeRemaining.toFloat(),
                            onValueChange = {},
                            valueRange = 0f..level.time.toFloat(),
                            colors = SliderDefaults.colors(
                                thumbColor = Color.Transparent, // Прозрачный ползунок
                                activeTrackColor = Color.Cyan,  // Цвет активного трека
                                inactiveTrackColor = Color.White // Цвет неактивного трека
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(36.dp)
                                .clip(RoundedCornerShape(20.dp)) // Закругление углов
                        )

                        // Наложенный текст на слайдер

                        OutlinedText(
                            text = "Time: ${timeRemaining}s",
                            outlineColor = Color.Black,
                            fillColor = Color.White,
                            fontSize = 18.sp ,
                            modifier = Modifier.align(Alignment.Center)
                        )

                    }
                }

                // Генерация уровня
                GenerateLevel(
                    N = level.N,
                    M = level.M,
                    circles = circles,
                    onCircleClick = { isCorrect, i, j ->
                        if (isCorrect) {
                            score += 1
                            circles = circles.toMutableList().apply {
                                val row = this[i].toMutableList()
                                row[j] = null
                                this[i] = row
                            }

                            if (circles.flatten().filterNotNull().all { !it.isCorrect }) {
                                timeRemaining += level.plusTime
                                circles = generateCirclesGrid(level.N, level.M, level.T)
                            }
                        } else {
                            timeRemaining -= level.outTime
                        }
                        SoundManager.playSound()
                    }
                )
            }
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
}

@Composable
fun GenerateLevel(
    N: Int,
    M: Int,
    circles: List<List<Circle?>>,
    onCircleClick: (Boolean, Int, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 0 until M) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (j in 0 until N) {
                    val circle = circles[i][j]
                    if (circle != null) {
                        CircleButton(circle.isCorrect) {

                            onCircleClick(circle.isCorrect, i, j)
                        }
                    } else {
                        Spacer(modifier = Modifier
                            .size(60.dp)
                            .padding(1.dp))
                    }
                }
            }
        }
    }
}

fun generateCirclesGrid(N: Int, M: Int, T: Int): List<List<Circle?>> {
    val correctCircles = mutableSetOf<Pair<Int, Int>>()
    while (correctCircles.size < T) {
        val i = (0 until M).random()
        val j = (0 until N).random()
        correctCircles.add(Pair(i, j))
    }

    return List(M) { i ->
        List(N) { j ->
            if (correctCircles.contains(Pair(i, j))) {
                Circle(true)
            } else {
                Circle(false)
            }
        }
    }
}

data class Circle(val isCorrect: Boolean)

@Composable
fun CircleButton(isCorrect: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(1.dp)
            .size(60.dp), // Размер круга
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isCorrect) Color(0xFF744D00) else Color(0xFFFFAA00)
        )
    ) {}
}
