package com.example.sheriffswordhunt.data.model

data class MissionQuestion (
    val id: Int,
    val caseId: Int,
    val prompt: String,
    val correctAnswer: String,
    val options: List<String>,
    val feedbackCorrect: String,
    val feedbackIncorrect: String
)
