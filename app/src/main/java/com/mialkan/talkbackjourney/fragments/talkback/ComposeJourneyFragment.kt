package com.mialkan.talkbackjourney.fragments.talkback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.mialkan.talkbackjourney.R
import com.mialkan.talkbackjourney.composeviews.AppBottomAppBar
import com.mialkan.talkbackjourney.composeviews.AppIconButton
import com.mialkan.talkbackjourney.composeviews.AppPrimaryButton
import com.mialkan.talkbackjourney.composeviews.AppTopAppBar
import kotlin.random.Random

class ComposeJourneyFirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                JourneyScreen(
                    fragmentNumber = 1,
                    findNavController(),
                    navigateTo = { dest ->
                        findNavController().navigate(
                            dest, bundleOf(),
                            NavOptions.Builder().setPopUpTo(R.id.talk_back_journey_compose_graph, true)
                                .build()
                        )
                    },
                    goBackToHome = {
                        findNavController().popBackStack(R.id.talk_back_journey_compose_graph, true)
                    }
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // !TextUtils.equals(accessibilityPaneTitle, mAccessibilityPaneTitle) title should be different.
        view.accessibilityPaneTitle = "Fragment 1 ${System.currentTimeMillis()}"
    }
}

class ComposeJourneySecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                JourneyScreen(
                    fragmentNumber = 2,
                    findNavController(),
                    navigateTo = { dest ->
                        findNavController().navigate(dest)
                    },
                    goBackToHome = {
                        findNavController().popBackStack(R.id.talk_back_journey_compose_graph, true)
                    }
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.accessibilityPaneTitle = "Fragment 2 ${System.currentTimeMillis()}"
    }
}

class ComposeJourneyThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                JourneyScreen(
                    fragmentNumber = 3,
                    findNavController(),
                    navigateTo = { dest ->
                        findNavController().navigate(dest)
                    },
                    goBackToHome = {
                        findNavController().popBackStack(R.id.talk_back_journey_compose_graph, true)
                    }
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.accessibilityPaneTitle = "Fragment 3 ${System.currentTimeMillis()}"
    }
}

@Composable
fun JourneyScreen(
    fragmentNumber: Int,
    navController: NavController,
    navigateTo: (dest: Int) -> Unit,
    goBackToHome: () -> Unit
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                AppTopAppBar(
                    title = "Fragment $fragmentNumber",
                    navController = navController,
                    navigationIcon = {
                        AppIconButton(onClick = { goBackToHome() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.btn_navigate_up),
                            )
                        }
                    }
                )
            },
            bottomBar = {
                JourneyBottomBar(
                    fragmentNumber = fragmentNumber,
                    navigateTo = navigateTo,
                    goBackToHome
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "This is Journey Fragment $fragmentNumber",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )

                JourneyButtonRow(rowIndex = 0, navigateTo = navigateTo)
                JourneyButtonRow(rowIndex = 1, navigateTo = navigateTo)
                JourneyButtonRow(rowIndex = 2, navigateTo = navigateTo)

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun JourneyButtonRow(rowIndex: Int, navigateTo: (dest: Int) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppPrimaryButton(onClick = {
            navigateTo(R.id.journeyComposeFirstFragment)
        }) {
            Text(
                text = Random.nextInt(0, 1000000).toString(),
            )
        }

        AppPrimaryButton(onClick = {
            navigateTo(R.id.journeyComposeSecondFragment)
        }) {
            Text(
                text = "Button " + ((rowIndex * 3) + 2)
            )
        }

        AppPrimaryButton(onClick = {
            navigateTo(R.id.journeyComposeThirdFragment)
        }) {
            Text(
                text = "Nav " + Random.nextInt(0, 1000)
            )
        }
    }
}

@Composable
fun JourneyBottomBar(
    fragmentNumber: Int,
    navigateTo: (dest: Int) -> Unit,
    goBackToHome: () -> Unit
) {

    AppBottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AppPrimaryButton(onClick = {
                when (fragmentNumber) {
                    1 -> {
                        goBackToHome()
                    }
                    2 -> {
                        navigateTo(R.id.journeyComposeFirstFragment)
                    }
                    else -> {
                        navigateTo(R.id.journeyComposeSecondFragment)
                    }
                }
            }) {
                Text(
                    text = if (fragmentNumber == 1) {
                        "Close"
                    } else {
                        "Previous"
                    }
                )
            }

            AppPrimaryButton(onClick = {
                when (fragmentNumber) {
                    1 -> {
                        navigateTo(R.id.journeyComposeSecondFragment)
                    }
                    2 -> {
                        navigateTo(R.id.journeyComposeThirdFragment)
                    }
                    else -> {
                        goBackToHome()
                    }
                }
            }) {
                Text(
                    text = if (fragmentNumber == 3) {
                        "Close"
                    } else {
                        "Next"
                    }
                )
            }
        }
    }
}
