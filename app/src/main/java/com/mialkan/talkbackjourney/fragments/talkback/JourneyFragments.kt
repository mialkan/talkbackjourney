package com.mialkan.talkbackjourney.fragments.talkback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.mialkan.talkbackjourney.R
import com.mialkan.talkbackjourney.databinding.FragmentJourneyBinding
import com.mialkan.talkbackjourney.fragments.BaseFragment

class JourneyFirstFragment : BaseFragment<FragmentJourneyBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentJourneyBinding = FragmentJourneyBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBarForJourney()
        viewBinding.journeyInfoTitle.text = "This is Journey Fragment 1"
        viewBinding.root.findViewById<Toolbar?>(R.id.toolbar)?.title = "Fragment 1"

        viewBinding.bottomBar.stepPreviousButton.text = "Close"
        viewBinding.bottomBar.stepPreviousButton.setOnClickListener {
            leaveJourney()
        }

        viewBinding.bottomBar.stepPrimaryButton.setOnClickListener {
            navigateToDestination(R.id.journeySecondFragment)
        }
        setUpJourneyContentButtonActions()
    }
}

class JourneySecondFragment : BaseFragment<FragmentJourneyBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentJourneyBinding = FragmentJourneyBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBarForJourney()
        viewBinding.journeyInfoTitle.text = "This is Journey Fragment 2"
        viewBinding.root.findViewById<Toolbar?>(R.id.toolbar)?.title = "Fragment 2"
        viewBinding.bottomBar.stepPreviousButton.setOnClickListener {
            navigateToDestination(R.id.journeyFirstFragment)
        }
        viewBinding.bottomBar.stepPrimaryButton.setOnClickListener {
            navigateToDestination(R.id.journeyThirdFragment)
        }
        setUpJourneyContentButtonActions()
    }
}

class JourneyThirdFragment : BaseFragment<FragmentJourneyBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentJourneyBinding = FragmentJourneyBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBarForJourney()
        viewBinding.journeyInfoTitle.text = "This is Journey Fragment 3"
        viewBinding.root.findViewById<Toolbar?>(R.id.toolbar)?.title = "Fragment 3"
        viewBinding.bottomBar.stepPrimaryButton.text = "Close"
        viewBinding.bottomBar.stepPrimaryButton.setOnClickListener {
            leaveJourney()
        }
        setUpJourneyContentButtonActions()
    }
}
