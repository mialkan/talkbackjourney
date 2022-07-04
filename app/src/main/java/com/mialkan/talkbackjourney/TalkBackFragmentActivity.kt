package com.mialkan.talkbackjourney

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mialkan.talkbackjourney.databinding.ActivityTalkBackFragmentBinding
import com.mialkan.talkbackjourney.fragments.talkback.JourneyFirstFragment
import com.mialkan.talkbackjourney.fragments.talkback.JourneySecondFragment
import com.mialkan.talkbackjourney.fragments.talkback.JourneyThirdFragment

class TalkBackFragmentActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityTalkBackFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTalkBackFragmentBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        navigateToFragment(JourneyFirstFragment.newInstance(), true)
    }

    fun navigateToDirection(@IdRes dir: Int) {
        when (dir) {
            R.id.journeyFirstFragment -> {
                navigateToFragment(JourneyFirstFragment.newInstance(), false)
            }
            R.id.journeySecondFragment -> {
                navigateToFragment(JourneySecondFragment.newInstance(), false)
            }
            R.id.journeyThirdFragment -> {
                navigateToFragment(JourneyThirdFragment.newInstance(), false)
            }
        }
    }

    private fun navigateToFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }
}
