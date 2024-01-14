package com.hmh.hamyeonham.challenge

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ChallengeState(
    val modifierState: ModifierState = ModifierState.DELETE,
)

enum class ModifierState {
    DELETE,
    CANCEL,
}

class ChallengeViewModel : ViewModel() {

    private val _challengeState = MutableStateFlow(ChallengeState())
    val challengeState = _challengeState.asStateFlow()

    fun updateState(transform: ChallengeState.() -> ChallengeState) {
        val currentState = challengeState.value
        val newState = currentState.transform()
        _challengeState.value = newState
    }

}
