package com.example.insync.presentation.constants

data class OnboardData(
    val index: Int,
    val title: String,
    val description: String,
    val imageResId: Int
)


val onboardData = listOf(
    OnboardData(
        index = 0,
        title = "Streamline Tasks with AI-Driven Email Management",
        description = "Leverage our AI to read and analyze your emails, schedule meetings, and manage tasks effortlessly.",
        imageResId = com.example.insync.R.drawable.ai_read
    ),
    OnboardData(
        index = 1,
        title = "Organize Your Inbox with Smart Email Filtering",
        description = "Experience a smarter inbox with filtered, relevant emails consolidated across multiple accounts.",
        imageResId = com.example.insync.R.drawable.message_find
    ),
    OnboardData(
        index = 2,
        title = "Effortless Scheduling and Time Management Made Simple",
        description = "Organize your life with seamless scheduling of dates and times, all within our app.",
        imageResId = com.example.insync.R.drawable.schedule
    )
)
