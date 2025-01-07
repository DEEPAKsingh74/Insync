
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingScreen() {
    val notificationEnabled = remember { mutableStateOf(true) }
    val darkModeEnabled = remember { mutableStateOf(false) }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Text(
                    text = "Settings",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Account Section
            item {
                SettingSection(title = "Account") {
                    SettingOption(
                        title = "Profile",
                        subtitle = "Manage your profile",
                        onClick = { /* Navigate to Profile Screen */ }
                    )
                    SettingOption(
                        title = "Privacy",
                        subtitle = "Control your privacy settings",
                        onClick = { /* Navigate to Privacy Screen */ }
                    )
                }
            }

            // Preferences Section
            item {
                SettingSection(title = "Preferences") {
                    SettingToggle(
                        title = "Enable Notifications",
                        isChecked = notificationEnabled.value,
                        onCheckedChange = { notificationEnabled.value = it }
                    )
                    SettingToggle(
                        title = "Dark Mode",
                        isChecked = darkModeEnabled.value,
                        onCheckedChange = { darkModeEnabled.value = it }
                    )
                }
            }

            // About Section
            item {
                SettingSection(title = "About") {
                    SettingOption(
                        title = "Help & Support",
                        subtitle = "Get help or contact support",
                        onClick = { /* Navigate to Support Screen */ }
                    )
                    SettingOption(
                        title = "About App",
                        subtitle = "Learn more about this app",
                        onClick = { /* Navigate to About Screen */ }
                    )
                }
            }
        }
    }

@Composable
fun SettingSection(title: String, content: @Composable () -> Unit) {
    Column {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        content()
    }
}

@Composable
fun SettingOption(title: String, subtitle: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = subtitle,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray
            ),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun SettingToggle(title: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )
        androidx.compose.material3.Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}
