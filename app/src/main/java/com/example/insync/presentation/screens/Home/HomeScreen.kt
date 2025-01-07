package com.example.insync.presentation.screens.Home

import InterestScreen
import ScheduleScreen
import SettingScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.insync.R

@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val navItems = listOf(
        NavItem("Schedule", R.drawable.calender),
        NavItem("Interest", R.drawable.interest),
        NavItem("Profile", R.drawable.profile)
    )

    var selectedNavItemIndex by remember {
        mutableIntStateOf(0)
    }

    Surface {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                if(selectedNavItemIndex == 1){
                    FloatingActionButton(
                        onClick = {
                        },
                        containerColor = Color.White,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            },
            bottomBar = {
                NavigationBar {
                    navItems.forEachIndexed{
                            index, item ->
                        NavigationBarItem(
                            selected = index == selectedNavItemIndex,
                            onClick = {
                                selectedNavItemIndex = index
                            },
                            icon = {
                                Image(
                                    painter = painterResource(item.icon),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            },
                            label = {
                                Text(item.label)
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            ContentScreen(
                modifier = Modifier.padding(innerPadding),
                selectedIndex = selectedNavItemIndex
            )
        }
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0
) {
    when(selectedIndex) {
        0 -> ScheduleScreen()
        1 -> InterestScreen()
        2 -> SettingScreen()
    }
}
