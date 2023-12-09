package com.example.city.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.city.R
import com.example.city.data.DataSource
import com.example.city.model.CategoryItem

@Composable
fun CategoryScreen(
    options: List<CategoryItem>,
    onCancelButtonClicked: () -> Unit,
    onSelectionChanged: (CategoryItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedCategory by rememberSaveable { mutableStateOf<CategoryItem?>(null) }

    LazyColumn(modifier = Modifier) {
        items(options.size) { index ->
            val categoryItem = options[index]
            CategoryItemCard(
                categoryItem = categoryItem,
                onSelectionChanged = {
                    selectedCategory = categoryItem
                    onSelectionChanged(categoryItem)
                },
            )
        }
    }

    // Pass the selected category to RecommendationScreen when it is not null
    selectedCategory?.let { category ->
        RecommendationScreen(
            selectedCategory = category,
            onCancelButtonClicked = onCancelButtonClicked,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .verticalScroll(rememberScrollState()),
        )
    }
}

@Composable
fun CategoryItemCard(categoryItem: CategoryItem, onSelectionChanged: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onSelectionChanged() }
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(text = categoryItem.name)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun CategoryPreview() {
    CategoryScreen(
        options = DataSource.CategoryItems,
        onCancelButtonClicked = {},
        onSelectionChanged = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState()),
    )
}
