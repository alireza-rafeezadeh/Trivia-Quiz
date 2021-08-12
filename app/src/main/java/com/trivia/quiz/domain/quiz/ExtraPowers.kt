package com.trivia.quiz.domain.quiz


data class ExtraPowers(
    var hasSkippedOneQuestion: Boolean = false,
    var hasRemovedTwoAnsers: Boolean = false,
    var hasAddTenSeconds: Boolean = false
) {

    fun reset() {
        hasSkippedOneQuestion = false
        hasRemovedTwoAnsers = false
        hasAddTenSeconds = false
    }

}