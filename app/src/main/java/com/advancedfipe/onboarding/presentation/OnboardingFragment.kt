package com.advancedfipe.onboarding.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.advancedfipe.R
import com.advancedfipe.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private val PAGE_PRICE = 1
    private val PAGE_FAVORITES = 2
    private val PAGE_GRAPHIC = 3
    private val PAGE_SHARE = 4

    private var _binding: FragmentOnboardingBinding? = null
    private val binding: FragmentOnboardingBinding get() = _binding!!

    private lateinit var onboardingViewModel: OnboardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentOnboardingBinding.inflate(inflater, container, false).apply {
        _binding = this
        onboardingViewModel = OnboardingViewModel()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNextButtonClick()
        setBackButtonClick()
        observerViewModel()
    }

    private fun getImageOnboarding(page: Int): Int {
        return when (page) {
            1 -> R.drawable.img_onboarding
            2 -> R.drawable.img_onboarding
            3 -> R.drawable.img_onboarding
            4 -> R.drawable.img_onboarding
            else -> 0
        }
    }

    private fun getTitle(page: Int): Int {
        return when (page) {
            1 -> R.string.text_title_onboarding_price
            2 -> R.string.text_title_onboarding_favorites
            3 -> R.string.text_title_onboarding_graphic
            4 -> R.string.text_title_onboarding_share
            else -> 0
        }
    }

    private fun getSubtitle(page: Int): Int {
        return when (page) {
            1 -> R.string.text_subtitle_onboarding_price
            2 -> R.string.text_subtitle_onboarding_favorites
            3 -> R.string.text_subtitle_onboarding_graphic
            4 -> R.string.text_subtitle_onboarding_share
            else -> 0
        }
    }

    private fun initPage(page: Int) {
        binding.apply {
            imageViewOnboarding.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), getImageOnboarding(page)
                )
            )
            textViewTitleOnboarding.text = getText(getTitle(page))
            textViewSubtitleOnboarding.text =
                getText(getSubtitle(page))
            getIndicator(page)
        }
    }

    private fun getIndicator(page: Int) {
        binding.apply {
            fun getImageIndicator(isOn: Boolean): Int {
                return if (isOn) R.drawable.ic_circle_onboarding_on else R.drawable.ic_circle_onboarding_off
            }
            imageViewFirst.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), getImageIndicator(page == PAGE_PRICE)
                )
            )
            imageViewSecond.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), getImageIndicator(page == PAGE_FAVORITES)
                )
            )
            imageViewThird.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), getImageIndicator(page == PAGE_GRAPHIC)
                )
            )
            imageViewFourth.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), getImageIndicator(page == PAGE_SHARE)
                )
            )
        }
    }

    private fun setNextButtonClick() {
        binding.buttonOnboardingNext.setOnClickListener {
            onboardingViewModel.next()
        }
    }

    private fun setBackButtonClick() {
        binding.buttonOnboardingBack.setOnClickListener {
            onboardingViewModel.back()
        }
    }

    private fun observerViewModel() {
        onboardingViewModel.navigate.observe(viewLifecycleOwner) {
            when (it) {
                is OnboardingState.NavigateSuccess -> {
                    initPage(it.page)
                }
                is OnboardingState.EndOnboarding -> {
                    nextPage(OnboardingFragmentDirections.actionOnboardingFragmentToOptionsConsultFragment())
                }
            }
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}