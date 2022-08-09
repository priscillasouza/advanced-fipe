package com.advancedfipe.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.advancedfipe.R
import com.advancedfipe.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding: FragmentOnboardingBinding get() = _binding!!

    private var page: Int = 0
    private val args: OnboardingFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentOnboardingBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNextButtonClick()
        initPage()
    }

    private fun initPage() {
        page = args.pages
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

    private fun getIndicator(page: Int) {
        binding.apply {
            fun getImageIndicator(isOn: Boolean): Int {
                return if (isOn) R.drawable.ic_circle_onboarding_on else R.drawable.ic_circle_onboarding_off
            }
            imageViewFirst.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), getImageIndicator(page == 1)
                )
            )
            imageViewSecond.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), getImageIndicator(page == 2)
                )
            )
            imageViewThird.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), getImageIndicator(page == 3)
                )
            )
            imageViewFourth.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), getImageIndicator(page == 4)
                )
            )
        }
    }

    private fun setNextButtonClick() {
        binding.buttonOnboardingNext.setOnClickListener {
            ++page
            nextPage(
                if(page < 5) {
                    OnboardingFragmentDirections.actionInitOnboardingSelf(
                        page
                    )
                }else {
                    OnboardingFragmentDirections.actionOnboardingFragmentToOptionsConsultFragment()
                }
            )
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}