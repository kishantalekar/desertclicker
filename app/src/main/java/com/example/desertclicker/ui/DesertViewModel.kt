package com.example.desertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.desertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DesertViewModel : ViewModel() {

    private val _uistate = MutableStateFlow(DesertUiState())

    val uiState: StateFlow<DesertUiState> = _uistate.asStateFlow()

    fun onDesertClicked() {
        _uistate.update { currentState ->
            val dessetsSold = currentState.desertSold + 1

            val nextDessertIndex = determineDessertIndex(dessetsSold)
            currentState.copy(
                currentDesertIndex = nextDessertIndex,
                revenue = currentState.revenue + currentState.currentDesertPrice,
                desertSold = dessetsSold,
                currentDesertPrice = dessertList[nextDessertIndex].price,
                currentDessertImageId = dessertList[nextDessertIndex].imageId

            )

        }
    }

    private fun determineDessertIndex(
        dessertSold: Int
    ): Int {
        var dessertIndex = 0
        for (index in dessertList.indices) {
            if (dessertSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertIndex
    }
}