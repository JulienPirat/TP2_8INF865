package com.example.tp2_8inf865

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tp2_8inf865.ui.screens.GameScreen
import com.example.tp2_8inf865.ui.screens.HomeScreen
import com.example.tp2_8inf865.ui.screens.ScreensList
import com.example.tp2_8inf865.ui.screens.StoryElementsScreen
import com.example.tp2_8inf865.ui.theme.TP2_8INF865Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TP2_8INF865Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    BuildScaffold()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildScaffold(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val items = listOf(
        ScreensList.Game,
        ScreensList.Home,
        ScreensList.StoryElements
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar (
                modifier = Modifier,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            when(screen.route){
                                "game" -> Icon(Icons.Filled.AccountCircle, contentDescription = stringResource(screen.resourceId))
                                "home" -> Icon(Icons.Filled.Home, contentDescription = stringResource(screen.resourceId))
                                "story_elements" -> Icon(Icons.Filled.Edit, contentDescription = stringResource(screen.resourceId))
                            }
                        },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(navController, startDestination = ScreensList.Home.route, Modifier.padding(it)) {
            composable("game") { GameScreen() }
            composable("home") { HomeScreen() }
            composable("story_elements") { StoryElementsScreen() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TP2_8INF865Theme {
        BuildScaffold()
    }
}