package com.oddzmint.lemon.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oddzmint.lemon.R
import com.oddzmint.lemon.presentation.ui.LemonPreview
import com.oddzmint.lemon.presentation.ui.model.MenuItemUi
import com.oddzmint.lemon.presentation.ui.theme.LemonTheme

@Composable
fun MenuItemCard(
    item: MenuItemUi,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 88.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 6.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "R${item.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

private val previewItem = MenuItemUi(
    id = 1,
    title = "Greek",
    price = "10",
    description = "Freshly prepared and served with Little Lemon style.",
    imageRes = R.drawable.greek
)

@Preview(showBackground = true, name = "Default")
@Composable
private fun MenuItemCardPreview() {
    LemonPreview {
        MenuItemCard(item = previewItem)
    }
}

@Preview(showBackground = true, name = "Long text")
@Composable
private fun MenuItemCardLongTextPreview() {
    LemonPreview {
        MenuItemCard(
            item = previewItem.copy(
                title = "Mediterranean Tuna Salad with Extra Olives and Feta",
                description = "A very long description that keeps going well past two lines" + "so we can confirm the card truncates it with an ellipsis and " + "doesn't grow taller than it should"
            )
        )
    }
}

@Preview(showBackground = true, name = "Dark", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MenuItemCardDarkPreview() {
    LemonPreview {
        MenuItemCard(
            item = previewItem
        )
    }
}

@Preview(showBackground = true, name = "Large font", fontScale = 1.5f)
@Composable
private fun MenuItemCardLargeFontPreview() {
    LemonPreview {
        MenuItemCard(item = previewItem)
    }
}