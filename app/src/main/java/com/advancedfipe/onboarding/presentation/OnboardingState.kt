package com.advancedfipe.onboarding.presentation

sealed class OnboardingState {
    class NavigateSuccess(val page: Int) : OnboardingState()
    object EndOnboarding : OnboardingState()
}