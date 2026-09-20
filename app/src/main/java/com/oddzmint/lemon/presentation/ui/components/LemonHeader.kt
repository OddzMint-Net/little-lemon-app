package com.oddzmint.lemon.presentation.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oddzmint.lemon.R
import com.oddzmint.lemon.presentation.ui.LemonPreview
import com.oddzmint.lemon.presentation.ui.theme.LemonTheme

@Composable
fun LemonHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.little_lemon_logo),
            modifier = Modifier.size(120.dp)
        )

        //TODO: add drawable -night logo variant for dark mode.
    }
}

@Preview(showBackground = true, name = "Light")
@Composable
private fun LemonHeaderPreview() {
    LemonPreview {
        LemonHeader()
    }
}

@Preview(showBackground = true, name = "Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LemonHeaderDarkPreview() {
    LemonPreview {
        Surface {
            LemonHeader()
        }
    }
}

@Preview(showBackground = true, name = "Wide screen", widthDp = 900)
@Composable
private fun LemonHeaderWidePreview() {
    LemonPreview {
        LemonHeader()
    }
}

@Preview(showBackground = true, name = "small screen", widthDp = 320)
@Composable
private fun LemonHeaderSmallPreview() {
    LemonPreview {
        LemonHeader()
    }
}