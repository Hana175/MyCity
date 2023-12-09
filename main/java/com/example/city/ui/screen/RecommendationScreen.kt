package com.example.city.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.city.R
import com.example.city.data.DataSource
import com.example.city.model.CategoryItem
import com.example.city.model.RecommendedItem

@Composable
fun RecommendationScreen(
    selectedCategory: CategoryItem?,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val recommendations = selectedCategory?.recommendations.orEmpty()
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_medium)),
//            .verticalScroll(rememberScrollState()),
    ) {
        // Display recommendations here
        recommendations.forEach { recommendation ->
            RecommendationItemCard(recommendation = recommendation)
        }

        // Add a cancel button
        CancelButton(onCancelButtonClicked)
    }
}

@Composable
fun CancelButton(onCancelButtonClicked: () -> Unit) {
    // You can customize the appearance and behavior of your cancel button here
    // For simplicity, I'm using a Text element as a button
    Text(
        text = "Cancel",
        modifier = Modifier
            .padding(16.dp)
            .clickable { onCancelButtonClicked() },
    )
}

@Composable
fun RecommendationItemCard(recommendation: RecommendedItem) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(text = recommendation.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = recommendation.description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationPreview() {
    // Assume you have the selected category from your ViewModel or other state management
    val selectedCategory = DataSource.CategoryItems.find { it.name == "KIDS" }

    // Use the selected category in the RecommendationScreen
    RecommendationScreen(
        selectedCategory = selectedCategory,
        onCancelButtonClicked = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState()),
    )
}
