package com.example.city

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.city.data.DataSource
import com.example.city.ui.screen.CategoryScreen
import com.example.city.ui.screen.CityViewModel
import com.example.city.ui.screen.RecommendationScreen
import com.example.city.ui.screen.StartScreen

enum class Screens(@StringRes val title: Int) {
    START(title = R.string.start),
    CATEGORY(title = R.string.choose_category),
    RECOMMENDATION(
        title = R.string.recomm,
    ),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                    )
                }
            }
        },
    )
}

@Composable
fun CityApp() {
    // build nav
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(
        backStackEntry?.destination?.route ?: Screens.START.name,
    )

    // Create ViewModel
    val viewModel: CityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CityAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.START.name,
            modifier = Modifier,
        ) {
            composable(route = Screens.START.name) {
                StartScreen(
                    onStartButtonClicked = {
                        navController.navigate(Screens.CATEGORY.name)
                    },
                    modifier = Modifier.verticalScroll(rememberScrollState()).padding(innerPadding),
                )
            }
            composable(route = Screens.CATEGORY.name) {
                CategoryScreen(
                    options = DataSource.CategoryItems,
                    onCancelButtonClicked = {
                        viewModel.resetChoices()
                        navController.popBackStack(Screens.START.name, inclusive = false)
                    },
                    onSelectionChanged = {
                        // Use the updateCategory function from the ViewModel
                        viewModel.updateCategory(it)
                        navController.navigate(Screens.RECOMMENDATION.name)
                    },
                    modifier = Modifier.verticalScroll(rememberScrollState()).padding(innerPadding),
                )
            }
            composable(route = Screens.RECOMMENDATION.name) {
                // Use the uiState in the RecommendationScreen
                RecommendationScreen(
                    selectedCategory = uiState.selectedCategory,
                    onCancelButtonClicked = {
                        viewModel.resetChoices()
                        navController.popBackStack(Screens.START.name, inclusive = false)
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState()),
                )
            }
        }
    }
}
