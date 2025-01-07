package com.example.insync.presentation.ui.theme

import android.provider.CalendarContract.Colors
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.insync.R

// Set of Material typography styles to start with
val AppTypography = Typography(

    // for large texts (e.g. the Onboarding page headings)
    bodyLarge = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.acme_regular, FontWeight.Bold)
        ),
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 40.sp,
        color = Color.Black,
        letterSpacing = 0.3.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.kanit_regular)
        ),
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        color = Color(25, 25, 25)
    )
)