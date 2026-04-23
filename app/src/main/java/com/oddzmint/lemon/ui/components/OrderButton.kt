package com.oddzmint.lemon.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.oddzmint.lemon.R

@Composable
fun OrderButton(
    isOrdered: Boolean,
    onClick: () -> Unit
) {
    val buttonText = if (isOrdered) {
        stringResource(R.string.order_button_sorted)
    } else {
        stringResource(id = R.string.order_button_sort)
    }

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isOrdered) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Sort,
            contentDescription = stringResource(R.string.sort_icon_description)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = buttonText)
    }
}