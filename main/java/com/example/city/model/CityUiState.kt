package com.example.city.model

data class CityUiState(
    val selectedCategory: CategoryItem? = null,
    val recommendedOptions: List<RecommendedItem> = emptyList(),
)
