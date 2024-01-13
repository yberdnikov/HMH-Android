package com.hmh.hamyeonham.challenge.generate

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.challenge.model.Challenge
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ChallengeGenerateViewState(
    val challenge: Challenge = Challenge()
)

@HiltViewModel
class ChallengeGenerateViewModel : ViewModel() {
    private val _state = MutableStateFlow(ChallengeGenerateViewState())
    val state = _state.asStateFlow()

    fun updateState(transform: ChallengeGenerateViewState.() -> ChallengeGenerateViewState) {
        val currentState = state.value
        val newState = currentState.transform()
        _state.value = newState
    }

    fun updateChallenge(transform: Challenge.() -> Challenge) {
        updateState {
            val currentState = state.value.challenge
            val challenge = currentState.transform()
            copy(challenge = challenge)
        }
    }

}
