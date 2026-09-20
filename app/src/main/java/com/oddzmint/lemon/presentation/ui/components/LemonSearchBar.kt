package com.oddzmint.lemon.presentation.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oddzmint.lemon.presentation.ui.theme.LemonTheme
import com.oddzmint.lemon.R
import com.oddzmint.lemon.presentation.ui.LemonPreview

@Composable
fun LemonSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = label,
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 600.dp),

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Preview(showBackground = true, name = "Empty")
@Composable
private fun LemonSearchBarEmptyPreview() {
    LemonPreview {
        LemonSearchBar(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.search_hint)) }
        )
    }
}

@Preview(showBackground = true, name = "With text")
@Composable
private fun LemonSearchBarFilledPreview() {
    LemonPreview {
        LemonSearchBar(
            value = "Greek",
            onValueChange = {},
            label = { Text(stringResource(R.string.search_hint)) }
        )
    }
}

@Preview(showBackground = true, name = "Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LemonSearchBarDarkPreview() {
    LemonPreview {
        LemonSearchBar(
            value = "Greek",
            onValueChange = {},
            label = { Text(stringResource(R.string.search_hint)) }
        )
    }
}

@Preview(showBackground = true, name = "Wide screen", widthDp = 900)
@Composable
private fun LemonSearchBarWidePreview() {
    LemonPreview {
        LemonSearchBar(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.search_hint)) }
        )
    }
}

@Preview(showBackground = true, name = "Interactive")
@Composable
private fun LemonSearchBarInteractivePreview() {
    LemonPreview {
        var text by remember { mutableStateOf("") }
        LemonSearchBar(
            value = text,
            onValueChange = { text = it },
            label = { Text(stringResource(R.string.search_hint)) }
        )
    }
}