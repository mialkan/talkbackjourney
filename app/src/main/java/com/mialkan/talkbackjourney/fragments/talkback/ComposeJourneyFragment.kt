package com.mialkan.talkbackjourney.fragments.talkback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.mialkan.talkbackjourney.R
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
                text = "Button " + Random.nextInt(0, 1000),
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
                text = "Button " + Random.nextInt(0, 1000)
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

@Composable
fun AppTopAppBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    navController: NavController,
    navigationIconVector: ImageVector = Icons.Filled.ArrowBack,
    navigationIcon: @Composable (() -> Unit)? = {
        AppIconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = navigationIconVector,
                contentDescription = stringResource(R.string.btn_navigate_up),
            )
        }
    }
) {
    AppTopAppBar(
        content = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.semantics { heading() }
            )
        },
        actions = actions,
        navController = navController,
        navigationIconVector = navigationIconVector,
        navigationIcon = navigationIcon
    )
}

@Composable
fun AppTopAppBar(
    content: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    navController: NavController,
    navigationIconVector: ImageVector = Icons.Filled.ArrowBack,
    navigationIcon: @Composable (() -> Unit)? = {
        AppIconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = navigationIconVector,
                contentDescription = stringResource(R.string.btn_navigate_up),
            )
        }
    }
) {
    var barNavigationIcon = navigationIcon
    navController.addOnDestinationChangedListener { _, _, _ ->
        if (navController.currentBackStackEntry == null) {
            barNavigationIcon = null
        }
    }

    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        title = content,
        navigationIcon = barNavigationIcon,
        actions = actions
    )
}

@Composable
fun AppIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        content = content
    )
}

@Composable
fun AppBottomAppBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val backgroundColor = MaterialTheme.colors.surface
    BottomAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColorFor(backgroundColor),
        content = content
    )
}

@Composable
fun AppPrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val defaultColor = MaterialTheme.colors.primary
    val disabledColor = MaterialTheme.colors.onPrimary
    val colors = ButtonDefaults.buttonColors(
        backgroundColor = contentColorFor(defaultColor),
        contentColor = defaultColor,
        disabledBackgroundColor = disabledColor.copy(alpha = 0.12f)
            .compositeOver(defaultColor),
        disabledContentColor = disabledColor
            .copy(alpha = ContentAlpha.disabled)
    )
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(30.dp),
        colors = colors,
        content = content
    )
}
