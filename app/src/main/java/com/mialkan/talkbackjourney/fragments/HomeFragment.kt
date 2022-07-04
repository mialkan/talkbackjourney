package com.mialkan.talkbackjourney.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.mialkan.talkbackjourney.R
import com.mialkan.talkbackjourney.TalkBackFragmentActivity
import com.mialkan.talkbackjourney.databinding.FragmentHomeBinding
import com.mialkan.talkbackjourney.extensions.setupToolbar

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setUpControls()
    }

    private fun setUpControls() {
        viewBinding.composeTalkBackJourneyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_compose_talk_back_journey)
        }

        viewBinding.talkBackJourneyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_talk_back_journey)
        }

        viewBinding.talkBackJourneyActivityBtn.setOnClickListener {
            startActivity(Intent(requireContext(), TalkBackFragmentActivity::class.java))
        }

        viewBinding.root.findViewById<Toolbar?>(R.id.toolbar)?.title = "TalkBack Journey"
        viewBinding.dateLayout.editText?.setOnClickListener {
            val materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                .build()
            materialDatePicker.addOnPositiveButtonClickListener { _ ->
            }
            materialDatePicker.show(this.childFragmentManager, MaterialDatePicker::class.java.name)
        }
    }
}
