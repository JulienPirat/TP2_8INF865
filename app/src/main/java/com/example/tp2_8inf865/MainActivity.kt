package com.example.tp2_8inf865

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
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
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TP2_8INF865Theme {

                val windowSize = calculateWindowSizeClass(activity = this)

                /*
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    BuildScaffold(windowSize = windowSize)
                }
                 */
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavigationBar(windowSize = windowSize);
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigation(modifier: Modifier = Modifier, items: List<ScreensList>, currentDestination: NavDestination?, navController: NavController) {
    NavigationBar(
        modifier = Modifier,
    ) {


        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen.route) {
                        "game" -> Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = stringResource(screen.resourceId)
                        )

                        "home" -> Icon(
                            Icons.Filled.Home,
                            contentDescription = stringResource(screen.resourceId)
                        )

                        "story_elements" -> Icon(
                            Icons.Filled.Edit,
                            contentDescription = stringResource(screen.resourceId)
                        )
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RailNavigation(modifier: Modifier = Modifier, items: List<ScreensList>, currentDestination: NavDestination?, navController: NavController) {
    NavigationRail (modifier = Modifier,
    ) {

        items.forEach { screen ->
            NavigationRailItem(
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBar(modifier: Modifier = Modifier, windowSize: WindowSizeClass) {
    val navController = rememberNavController()

    val items = listOf(
        ScreensList.Game,
        ScreensList.Home,
        ScreensList.StoryElements
    )

    val showNavigationRail = windowSize.widthSizeClass != WindowWidthSizeClass.Compact
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if(!showNavigationRail){
                BottomNavigation(modifier = Modifier, items = items, currentDestination = currentDestination, navController = navController)
            }
        },
        topBar = {
            Column {
                if(showNavigationRail) {
                    RailNavigation(modifier = Modifier, items = items, currentDestination = currentDestination, navController = navController)
                }
            }
        },
        content = {
                NavHost(
                    navController,
                    startDestination = ScreensList.Home.route,
                ) {
                    if(!showNavigationRail){
                        composable("game") { GameScreen(10) }
                        composable("home") { HomeScreen(10) }
                        composable("story_elements") { StoryElementsScreen(10) }
                     }else{
                        composable("game") { GameScreen() }
                        composable("home") { HomeScreen() }
                        composable("story_elements") { StoryElementsScreen()
                    }
                }
        }
    })
}