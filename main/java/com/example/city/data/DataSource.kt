package com.example.city.data

import com.example.city.model.CategoryItem
import com.example.city.model.CoffeeItem
import com.example.city.model.KidsRecommendedItem
import com.example.city.model.RestaurantItem
import com.example.city.model.ShoppingItem

object DataSource {

    val CategoryItems = listOf(
        CategoryItem(
            name = "KIDS",
            recommendations = listOf(
                KidsRecommendedItem("KidZania", "An interactive city made for children."),
                KidsRecommendedItem("Magic Galaxy", "Kids' exploration maxed out!"),
            ),
        ),
        CategoryItem(
            name = "COFFEE_SHOPS",
            recommendations = listOf(
                CoffeeItem("Cilantro", "Fresh coffee for everyone!"),
                CoffeeItem("CAF", "Best Spanish lattes."),
            ),
        ),
        CategoryItem(
            name = "RESTAURANTS",
            recommendations = listOf(
                RestaurantItem("Otto8", "Exquisite Italian dining experience."),
                RestaurantItem("DON Eatery", "A blend of asian."),
            ),
        ),
        CategoryItem(
            name = "SHOPPING_MALLS",
            recommendations = listOf(
                ShoppingItem("CFCM", "One-stop destination for shopping and entertainment."),
                ShoppingItem("Downtown", "Explore the latest trends in fashion."),
            ),
        ),
    )
}
