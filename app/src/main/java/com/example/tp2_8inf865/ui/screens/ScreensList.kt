package com.example.tp2_8inf865.ui.screens

import androidx.annotation.StringRes
import com.example.tp2_8inf865.R

sealed class ScreensList(val route: String, @StringRes val resourceId: Int) {
    object Home : ScreensList("home", R.string.home)
    object Game : ScreensList("game", R.string.games)
    object StoryElements : ScreensList("story_elements", R.string.story_elements)
}