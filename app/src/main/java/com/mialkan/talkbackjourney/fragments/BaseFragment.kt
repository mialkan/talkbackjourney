package com.mialkan.talkbackjourney.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.button.MaterialButton
import com.mialkan.talkbackjourney.R
import com.mialkan.talkbackjourney.extensions.setupToolbar

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _viewBinding: VB? = null
    val viewBinding: VB get() = requireNotNull(_viewBinding)

    abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = createViewBinding(inflater, container)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    protected fun isViewBindingSet(): Boolean = _viewBinding != null

    fun setUpToolBarForJourney() {
        setupToolbar(true) { leaveJourney() }
    }

    fun leaveJourney() {
        findNavController().popBackStack(R.id.talk_back_journey_nav_graph, true)
    }

    fun navigateToDestination(@IdRes dest: Int) {
        findNavController().navigate(
            dest,
            bundleOf(),
            NavOptions.Builder().setPopUpTo(R.id.talk_back_journey_nav_graph, true).build()
        )
    }

    fun setUpJourneyContentButtonActions() {
        viewBinding.root.findViewById<MaterialButton>(R.id.btn1)?.run {
            this.text = "Button 1"
            setOnClickListener {
                navigateToDestination(R.id.journeyFirstFragment)
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn4)?.run {
            this.text = "Button 4"
            setOnClickListener {
                navigateToDestination(R.id.journeyFirstFragment)
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn7)?.run {
            this.text = "Button 7"
            setOnClickListener {
                navigateToDestination(R.id.journeyFirstFragment)
            }
        }

        viewBinding.root.findViewById<MaterialButton>(R.id.btn2)?.run {
            this.text = "Button 2"
            setOnClickListener {
                navigateToDestination(R.id.journeySecondFragment)
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn5)?.run {
            this.text = "Button 5"
            setOnClickListener {
                navigateToDestination(R.id.journeySecondFragment)
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn8)?.run {
            this.text = "Button 8"
            setOnClickListener {
                navigateToDestination(R.id.journeySecondFragment)
            }
        }

        viewBinding.root.findViewById<MaterialButton>(R.id.btn3)?.run {
            this.text = "Button 3"
            setOnClickListener {
                navigateToDestination(R.id.journeyThirdFragment)
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn6)?.run {
            this.text = "Button 6"
            setOnClickListener {
                navigateToDestination(R.id.journeyThirdFragment)
            }
        }
        viewBinding.root.findViewById<MaterialButton>(R.id.btn9)?.run {
            this.text = "Button 9"
            setOnClickListener {
                navigateToDestination(R.id.journeyThirdFragment)
            }
        }
    }
}
