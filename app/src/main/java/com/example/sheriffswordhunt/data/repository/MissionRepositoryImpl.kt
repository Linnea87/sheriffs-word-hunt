package com.example.sheriffswordhunt.data.repository

import com.example.sheriffswordhunt.data.model.MissionCase
import com.example.sheriffswordhunt.data.model.MissionQuestion

class MissionRepositoryImpl : MissionRepository {

    private val cases = listOf(
        MissionCase(
            id = 1,
            title = "The Lost Verbs",
            subtitle = "Outlaw Clay McCoy stole our action words. Restore each missing verb."
        ),
        MissionCase(
            id = 2,
            title = "The Broken Sentence",
            subtitle = "Bandit Lila Graves shattered our sentences. Choose the correct linking words."
        ),
        MissionCase(
            id = 3,
            title = "The Vanishing Nouns",
            subtitle = "Outlaw Jasper “Dust” Kane hid all the nouns around town. Recover each missing object."
        )
    )

    private val questions = listOf(
        // Case 1 – The Lost Verbs
        MissionQuestion(
            id = 1,
            caseId = 1,
            prompt = "The cowboy ____ across the desert.",
            correctAnswer = "rode",
            options = listOf("ride", "rode", "riding"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 2,
            caseId = 1,
            prompt = "The sheriff ____ after the outlaw.",
            correctAnswer = "ran",
            options = listOf("run", "ran", "running"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 3,
            caseId = 1,
            prompt = "The horse ____ over the fence.",
            correctAnswer = "jumped",
            options = listOf("jump", "jumped", "jumping"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 4,
            caseId = 1,
            prompt = "The marshal ____ the clues carefully.",
            correctAnswer = "examined",
            options = listOf("examine", "examined", "examining"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 5,
            caseId = 1,
            prompt = "The deputy ____ his horse before sunrise.",
            correctAnswer = "saddled",
            options = listOf("saddle", "saddled", "saddling"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 6,
            caseId = 1,
            prompt = "The outlaw ____ behind the old saloon.",
            correctAnswer = "hid",
            options = listOf("hide", "hid", "hiding"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        // Case 2 – The Broken Sentence
        MissionQuestion(
            id = 7,
            caseId = 2,
            prompt = "The sheriff was tired, ____ he kept riding.",
            correctAnswer = "but",
            options = listOf("but", "so", "because"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 8,
            caseId = 2,
            prompt = "It started to rain, ____ the cowboys hurried inside.",
            correctAnswer = "so",
            options = listOf("so", "and", "but"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 9,
            caseId = 2,
            prompt = "The saloon was noisy, ____ the street outside was quiet.",
            correctAnswer = "but",
            options = listOf("but", "because", "so"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 10,
            caseId = 2,
            prompt = "The bandit ran out the door ____ jumped on his horse.",
            correctAnswer = "and",
            options = listOf("and", "but", "or"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 11,
            caseId = 2,
            prompt = "Clay McCoy is clever, ____ he is not faster than the law.",
            correctAnswer = "but",
            options = listOf("but", "and", "so"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 12,
            caseId = 2,
            prompt = "The train was late, ____ everyone waited at the station.",
            correctAnswer = "so",
            options = listOf("so", "because", "and"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        // Case 3 – The Vanishing Nouns
        MissionQuestion(
            id = 13,
            caseId = 3,
            prompt = "The cowboy placed his hat on the ____.",
            correctAnswer = "table",
            options = listOf("table", "horse", "river"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 14,
            caseId = 3,
            prompt = "A loud sound came from the old ____.",
            correctAnswer = "barn",
            options = listOf("barn", "desert", "sheriff"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 15,
            caseId = 3,
            prompt = "The deputy wrote the clues in his ____.",
            correctAnswer = "notebook",
            options = listOf("notebook", "horse", "boot"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 16,
            caseId = 3,
            prompt = "The outlaw hid behind a large ____.",
            correctAnswer = "rock",
            options = listOf("rock", "lamp", "rope"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 17,
            caseId = 3,
            prompt = "A map showed a secret ____ in the canyon.",
            correctAnswer = "path",
            options = listOf("path", "train", "bandit"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        ),
        MissionQuestion(
            id = 18,
            caseId = 3,
            prompt = "The sheriff kept his badge in a small wooden ____.",
            correctAnswer = "box",
            options = listOf("box", "horse", "desert"),
            feedbackCorrect = "⭐ Correct, deputy.\nThe word was recovered!",
            feedbackIncorrect = "❌ Not quite, deputy.\nTry again!"
        )

    )

    override fun getCases(): List<MissionCase> = cases

    override fun getCaseById(id: Int): MissionCase? {
        return cases.firstOrNull { it.id == id }
    }


    override fun getQuestionsForCase(caseId: Int): List<MissionQuestion> {
       return questions.filter { it.caseId == caseId }
    }
}