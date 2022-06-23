package com.mialkan.talkbackjourney.composeviews

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mialkan.talkbackjourney.R

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
