package com.example.desertclicker.ui

import androidx.annotation.DrawableRes
import com.example.desertclicker.data.Datasource.dessertList

data class DesertUiState(
    val currentDesertIndex: Int = 0,
    val revenue: Int = 0,
    val desertSold: Int = 0,
    val currentDesertPrice: Int = dessertList[currentDesertIndex].price,
    @DrawableRes val currentDessertImageId: Int = dessertList[currentDesertIndex].imageId
)