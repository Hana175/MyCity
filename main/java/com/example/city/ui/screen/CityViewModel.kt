package com.example.city.ui.screen

import androidx.lifecycle.ViewModel
import com.example.city.model.CategoryItem
import com.example.city.model.CityUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState.asStateFlow()

    fun updateCategory(category: CategoryItem) {
        _uiState.update { currentState ->
            val previousCategory = currentState.selectedCategory
            val recommendedOptions = previousCategory?.recommendations.orEmpty()

            val newState = currentState.copy(
                selectedCategory = category,
                recommendedOptions = recommendedOptions,
            )

            // Log the newState
            println("New State: $newState")

            currentState.copy(
                selectedCategory = category,
                recommendedOptions = recommendedOptions,
            )
        }
    }

    fun resetChoices() {
        _uiState.value = CityUiState()
    }
}
