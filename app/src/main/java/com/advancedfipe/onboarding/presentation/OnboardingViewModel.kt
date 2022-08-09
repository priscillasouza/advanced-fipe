package com.advancedfipe.onboarding.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnboardingViewModel : ViewModel() {

    private val MAX_PAGE = 4
    private var page: Int = 1

    private var _navigate: MutableLiveData<OnboardingState> = MutableLiveData()
    val navigate: LiveData<OnboardingState> = _navigate

    fun next() {
        if (page < MAX_PAGE) {
            page++
            _navigate.postValue(OnboardingState.NavigateSuccess(page))
        } else {
            _navigate.postValue(OnboardingState.EndOnboarding)
        }
    }

    fun back() {
        if (page >= 1) {
            --page
            _navigate.postValue(OnboardingState.NavigateSuccess(page))
        }
    }
}