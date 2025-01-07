
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.insync.R
import com.example.insync.data.model.ScheduleEntity
import com.example.insync.presentation.ViewModels.ScheduleViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.jar.Manifest

@Composable
fun ScheduleScreen(scheduleViewModel: ScheduleViewModel = hiltViewModel()) {
    val tags = listOf("Custom", "Email", "History")

    // State to manage the visibility of the dialog
    var showDialog by remember { mutableStateOf(false) }
    var selectedChip by remember { mutableIntStateOf(0) }

    val scheduleItems by scheduleViewModel.scheduleItems

    // Remember the permission state for notifications
//    val notificationPermissionState = rememberPermissionState(
//        permission = Manifest.permission.POST_NOTIFICATIONS
//    )
//
//
//    // Automatically request permission if not granted
//    LaunchedEffect(Unit) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !notificationPermissionState.hasPermission) {
//            notificationPermissionState.launchPermissionRequest()
//        }
//    }

    // Initial load
    LaunchedEffect(selectedChip) {
        scheduleViewModel.loadSchedules(tags[selectedChip])
    }


    // create dialog
    if (showDialog) {
        AddSchedule(
            onDismiss = { showDialog = false }, // To close the dialog
            onSave = { title, description, date, time, priority ->
                val schedule = ScheduleEntity(
                    name = title,
                    description = description,
                    date = date,
                    time = time,
                )

                scheduleViewModel.insertSchedule(schedule, tags[selectedChip])
                showDialog = false
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        LazyColumn {

            //header bar
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp) // Adjusted height for better proportions
                        .padding(horizontal = 16.dp), // Added padding for layout margins
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // App Logo
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(end = 8.dp), // Adds space between logo and icons
                        painter = painterResource(R.drawable.insync_logo),
                        contentDescription = "App logo"
                    )

                    // Notification Icons
                    Row(
                        modifier = Modifier.padding(start = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between icons
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // First Clickable Icon
                        Image(
                            painter = painterResource(id = R.drawable.multiple),
                            contentDescription = "First Icon",
                            modifier = Modifier
                                .size(24.dp) // Ensure consistent size
                                .clickable {
                                    showDialog = !showDialog
                                }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.bell),
                            contentDescription = "First Icon",
                            modifier = Modifier
                                .size(24.dp) // Ensure consistent size
                                .clickable {
                                    // Handle first icon click
                                }
                        )
                    }

                }
            }





            // Horizontal Tags
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(tags) { index, tag ->
                        Chip(
                            text = tag,
                            onClick = {
                                selectedChip = index
                            },
                            isSelected = index == selectedChip
                        )
                    }
                }
            }

            // List Items
            itemsIndexed(scheduleItems) { index, item ->
                Column {
                    ScheduleItem(
                        item = item,
                        onEdit = {
                            if(selectedChip == 2){
                                scheduleViewModel.restoreSchedule(item.id, tags[selectedChip]);
                            }
                        },
                        onDelete = {
                            scheduleViewModel.deleteSchedule(item.id, tags[selectedChip])
                        },
                        selectedChip = selectedChip
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun Chip(
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean = false
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(
                color = if (isSelected) Color(0xFF3F51B5) else Color(0xFFBBE9FB), // Dark blue if selected, light blue if unselected
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) Color(0xFF3F51B5) else Color(0xFFBBE9FB), // Border color matches the background when selected
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isSelected) Color.White else Color(0xFF3F51B5) // White text when selected, dark blue when unselected
            )
        )
    }
}


@Composable
fun ScheduleItem(
    item: ScheduleEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    selectedChip: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF) // Replace with your desired color
        ),
        shape = RoundedCornerShape(12.dp) // Slightly more rounded corners for a modern look
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Timer Section
            Column(
                modifier = Modifier
                    .width(80.dp) // Fixed width for a consistent layout
                    .padding(end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.time,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF3F51B5) // Accent color for better visibility
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.date,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.Gray // Subtle color for secondary text
                    )
                )
            }


            Spacer(modifier = Modifier.width(12.dp))

            // Right Content Section
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = item.name,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(0xFF212121) // Dark color for primary text
                        )
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            onClick = {
                                onEdit()
                            }
                        ) {
                            Image(
                                painter = painterResource(
                                    if (selectedChip == 2) R.drawable.restore else R.drawable.edit
                                ),
                                contentDescription = "Edit icon",
                                modifier = Modifier.size(18.dp)
                            )

                        }
                        IconButton(
                            onClick = {
                                onDelete()
                            }
                        ) {
                            Image(
                                painter = painterResource(R.drawable.delete_red),
                                contentDescription = "Edit icon",
                                modifier = Modifier
                                    .size(18.dp)
                            )
                        }
                    }
                }
                Text(
                    text = item.description,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray // Subtle color for secondary text
                    ),
                    maxLines = 3, // Limit text to two lines
                    overflow = TextOverflow.Ellipsis // Add ellipsis if text overflows
                )
            }
        }
    }
}

@Composable
fun AddSchedule(
    onDismiss: () -> Unit,
    onSave: (String, String, String, String, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val todayDate = remember {
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        formatter.format(calendar.time)
    }

    var date by remember { mutableStateOf(todayDate) }
    var time by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("") }



    // AlertDialog for adding schedule
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Create Schedule") },
        text = {
            Column(modifier = Modifier
                .padding(horizontal = 2.dp, vertical = 16.dp)
            ) {
                TitleInput(title) { title = it }
                Spacer(modifier = Modifier.height(8.dp))
                DescriptionInput(description) { description = it }
                Spacer(modifier = Modifier.height(8.dp))
                DateInput(date) { newDate ->
                    date = newDate
                }
                Spacer(modifier = Modifier.height(8.dp))
                TimeInput(time) { newTime ->
                    time = newTime
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotEmpty() && time.isNotEmpty()) {
                        onSave(title, description, date, time, priority) // Perform save action
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp) // Add padding for spacing
                    .height(48.dp), // Consistent height
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2FACDC), // Primary button color
                    contentColor = Color.White // Text color
                ),
                shape = RoundedCornerShape(12.dp), // Rounded corners
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp, // Subtle shadow
                    pressedElevation = 8.dp
                )
            ) {
                Text(
                    text = "Create",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = { onDismiss() }, // Handle dismiss action
                modifier = Modifier
                    .padding(horizontal = 8.dp) // Add padding for spacing
                    .height(48.dp), // Consistent height
                shape = RoundedCornerShape(12.dp), // Rounded corners
                border = BorderStroke(
                    width = 1.dp,
                    color = Color(0xFF9E9E9E) // Neutral gray border color
                )
            ) {
                Text(
                    text = "Cancel",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color.Black // Text color
                    )
                )
            }
        }
    )
}

// Title Input
@Composable
fun TitleInput(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                "Title",
                style = TextStyle(
                fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )
            )
                      },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(8.dp), // Rounded corners
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF3F51B5), // Blue border when focused
            unfocusedBorderColor = Color(0xFF9E9E9E), // Gray border when not focused
        )
    )
}


// Description Input
@Composable
fun DescriptionInput(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Description",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )
        )
                },
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = false,
        maxLines = 3,
        shape = RoundedCornerShape(12.dp), // Rounded corners for the border
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF3F51B5), // Blue border when focused
            unfocusedBorderColor = Color(0xFF9E9E9E), // Gray border when not focused
        )
    )
}

// Date Input
@Composable
fun DateInput(value: String, onDateSelected: (String) -> Unit) {
    var showDatePicker by remember { mutableStateOf(false) }

    // Box for the input UI
    DateInputBox(value, showDatePicker) { showDatePicker = true }

    // Date picker dialog
    if (showDatePicker) {
        ShowDatePickerDialog(
            onDateSelected = { selectedDate ->
                onDateSelected(selectedDate)
                showDatePicker = false // Close the picker
            },
            onDismiss = { showDatePicker = false }
        )
    }
}

@Composable
fun DateInputBox(value: String, showPicker: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() } // Trigger onClick when tapped
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp) // Rounded corners
            )
            .border(
                width = 1.dp,
                color = if (showPicker) Color(0xFF3F51B5) else Color(0xFF9E9E9E), // Dynamic border color
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.calender),
                contentDescription = "Calendar Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (value.isEmpty()) "Select Date" else value,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun ShowDatePickerDialog(onDateSelected: (String) -> Unit, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    // Show date picker dialog
    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val selectedDate = "%02d/%02d/%d".format(dayOfMonth, month + 1, year)
            onDateSelected(selectedDate) // Pass the formatted date
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).apply {
        setOnDismissListener { onDismiss() } // Handle dismiss action
    }.show()
}



// Time Input
@Composable
fun TimeInput(value: String, onTimeSelected: (String) -> Unit) {
    var showPicker by remember { mutableStateOf(false) }

    TimeInputBox(value, showPicker) { showPicker = true }

    if (showPicker) {
        ShowTimePicker(onTimeSelected) { showPicker = false }
    }
}

@Composable
fun TimeInputBox(value: String, showPicker: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() } // Show picker on click
            .background(
                color = Color.Transparent, // Transparent background for the clickable area
                shape = RoundedCornerShape(8.dp) // Rounded corners
            )
            .border(
                width = 1.dp,
                color = if (showPicker) Color(0xFF3F51B5) else Color(0xFF9E9E9E), // Change border color based on state
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp) // Padding inside the border
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.time),
                contentDescription = "Time Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
            Text(
                text = if (value.isEmpty()) "Select Time" else value, // Show placeholder or selected time
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black // Text color
                )
            )
        }
    }
}

@Composable
fun ShowTimePicker(onTimeSelected: (String) -> Unit, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            // Format the selected time
            val amPm = if (hourOfDay < 12) "AM" else "PM"
            val hour = if (hourOfDay % 12 == 0) 12 else hourOfDay % 12
            val formattedTime = "%02d:%02d %s".format(hour, minute, amPm)
            onTimeSelected(formattedTime) // Pass the selected time
            onDismiss() // Close the picker
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false // Use 12-hour format; change to `true` for 24-hour format
    ).show()
}
