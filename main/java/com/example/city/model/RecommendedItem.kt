package com.example.city.model

sealed class RecommendedItem(open val name: String, open val description: String)

data class KidsRecommendedItem(
    override val name: String,
    override val description: String,
) : RecommendedItem(name, description)

data class CoffeeItem(
    override val name: String,
    override val description: String,
) : RecommendedItem(name, description)

data class RestaurantItem(
    override val name: String,
    override val description: String,
) : RecommendedItem(name, description)

data class ShoppingItem(
    override val name: String,
    override val description: String,
) : RecommendedItem(name, description)
