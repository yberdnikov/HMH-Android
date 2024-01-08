package com.hmh.hamyeonham.feature.onboarding.viewModel

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.feature.onboarding.OnBoardingFragmentType
import com.hmh.hamyeonham.feature.onboarding.model.OnBoardingQuestion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class OnBoardingSelectDataState(
    val onBoardingQuestion: OnBoardingQuestion = OnBoardingQuestion(),
)

@HiltViewModel
class OnBoardingSelectDataViewModel @Inject constructor() : ViewModel() {
    private val _onBoardingSelectDataState = MutableStateFlow(OnBoardingSelectDataState())
    val onBoardingSelectDataState = _onBoardingSelectDataState.asStateFlow()

    fun initQuestionData(fragmentType: OnBoardingFragmentType) {
        val onBoardingQuestionTime = OnBoardingQuestion(
            "하루 평균 휴대폰을\n얼마나 사용하시나요?",
            "",
            listOf("1-4시간", "4-8시간", "8-12시간", "12시간 이상")
        )

        val onBoardingQuestionProblem = OnBoardingQuestion(
            "휴대폰을 사용할 때\n어떤 문제를 겪고 계시나요?",
            "해당 문항은 최대 2개의 복수 응답이 가능해요",
            listOf("중독이 너무 심해요", "무의식적으로 사용하게 돼요", "스스로 제어가 안돼요", "일상생활에 영향을 끼쳐요")
        )

        val onBoardingChallengePeriod = OnBoardingQuestion(
            "챌린지 기간을 선택해주세요",
            "첫 챌린지로 가볍게 도전하기 좋은 7일을 추천해요!",
            listOf("7일", "14일", "21일", "30일")
        )

        _onBoardingSelectDataState.value = onBoardingSelectDataState.value.copy(
            onBoardingQuestion = when (fragmentType) {
                OnBoardingFragmentType.SELECT_DATA_TIME -> onBoardingQuestionTime
                OnBoardingFragmentType.SELECT_DATA_PROBLEM -> onBoardingQuestionProblem
                OnBoardingFragmentType.SELECT_DATA_PERIOD -> onBoardingChallengePeriod
                else -> OnBoardingQuestion()
            }
        )
    }
}
