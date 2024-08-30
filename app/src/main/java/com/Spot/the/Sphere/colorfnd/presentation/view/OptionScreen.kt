package com.Spot.the.Sphere.colorfnd.presentation.view

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Color.Sphere.Challenge.gamecolor.data.Prefs
import com.Color.Sphere.Challenge.gamecolor.data.SoundManager
import com.Spot.the.Sphere.colorfnd.R
import com.Spot.the.Sphere.colorfnd.presentation.navigation.OutlinedText


@Composable
fun OptionsScreen(onBackClicked: () -> Unit) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    var musicVolume by remember { mutableFloatStateOf(Prefs.music) }
    var soundVolume by remember { mutableFloatStateOf(Prefs.sound) }

    // Обновление значений громкости при изменении
    LaunchedEffect(musicVolume) {
        Prefs.music = musicVolume
        SoundManager.setVolumeMusic()
    }

    LaunchedEffect(soundVolume) {
        Prefs.sound = soundVolume
        SoundManager.setVolumeSound()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB6C1)) // Используйте нужный цвет или фон
    ) {
        Image(
            painter = painterResource(id = R.drawable.background), // Замените на ваше фоновое изображение
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Box с фоном main_rec для всего экрана опций
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_rec), // Замените на ваше изображение main_rec
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxWidth()
                )

                Column(
                    modifier = Modifier
                        .padding(0.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Заголовок "Options"
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 0.dp)
                            .offset(y = -56.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        OutlinedText(
                            text = "Options",
                            outlineColor = Color.Black,
                            fillColor = Color.White,
                            fontSize = 50.sp
                        )
                    }
                    // Music Volume
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp, vertical = 8.dp)
                    ) {
                        OutlinedText(
                            text = "Music",
                            outlineColor = Color.Black,
                            fillColor = Color.White,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Slider(
                            value = musicVolume,
                            onValueChange = {  newValue ->
                                musicVolume = newValue
                                sharedPreferences.edit().putFloat("musicVolume", newValue).apply()
                            },
                            colors = SliderDefaults.colors(
                                thumbColor = Color(0xFF87FF7A), // Цвет ползунка
                                activeTrackColor = Color(0xFF87FF7A), // Цвет активного трека
                                inactiveTrackColor = Color.White, // Цвет неактивного трека
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            valueRange = 0f..1f,
                            steps = 10 // Можно настроить количество шагов
                        )
                    }


                    // Sound Volume
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp, vertical = 8.dp)
                    ) {
                        OutlinedText(
                            text = "Sound",
                            outlineColor = Color.Black,
                            fillColor = Color.White,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Slider(
                            value = soundVolume,
                            onValueChange = {newValue ->
                                soundVolume = newValue
                                sharedPreferences.edit().putFloat("soundVolume", newValue).apply()
                            },
                            colors = SliderDefaults.colors(
                                thumbColor = Color(0xFF87FF7A), // Цвет ползунка
                                activeTrackColor = Color(0xFF87FF7A), // Цвет активного трека
                                inactiveTrackColor = Color.White, // Цвет неактивного трека
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            valueRange = 0f..1f,
                            steps = 10 // Можно настроить количество шагов
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Exit Game Button
                }
            }

            // Spacer, чтобы "Back" был снизу
            Spacer(modifier = Modifier.weight(1f))

            // Back Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rec), // Фон для кнопки
                    contentDescription = null,

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onBackClicked)
                )
                OutlinedText(
                    text = "Back",
                    outlineColor = Color.Black,
                    fillColor = Color.White,
                    fontSize = 50.sp
                )
            }
        }
    }
}


