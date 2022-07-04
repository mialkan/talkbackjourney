package com.mialkan.talkbackjourney.extensions

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mialkan.talkbackjourney.R

fun Fragment.setupToolbar(
    showCloseNavIcon: Boolean = false,
    clickListener: View.OnClickListener? = null
) {
    view?.findViewById<Toolbar>(R.id.toolbar)?.apply {
        try {
            val navController = findNavController()
            setupWithNavController(
                navController
            )
            clickListener?.let { listener -> setNavigationOnClickListener(listener) }
            navController.addOnDestinationChangedListener { _, _, _ ->
                navigationIcon =
                    if (navController.currentBackStackEntry == null) {
                        null
                    } else {
                        ContextCompat.getDrawable(
                            context,
                            if (showCloseNavIcon) R.drawable.ic_close else R.drawable.ic_back
                        )
                    }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
