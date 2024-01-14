package com.hmh.hamyeonham.challenge

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class ChallengeState(
    val modifierState: ModifierState = ModifierState.DELETE,
)

enum class ModifierState {
    DELETE,
    CANCEL,
}

@HiltViewModel
class ChallengeViewModel @Inject constructor() : ViewModel() {

    private val _challengeState = MutableStateFlow(ChallengeState())
    val challengeState = _challengeState.asStateFlow()

    fun updateState(transform: ChallengeState.() -> ChallengeState) {
        val currentState = challengeState.value
        val newState = currentState.transform()
        _challengeState.value = newState
    }

}
