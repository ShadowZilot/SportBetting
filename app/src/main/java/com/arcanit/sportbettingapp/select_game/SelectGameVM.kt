package com.arcanit.sportbettingapp.select_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arcanit.sportbettingapp.commons.MoneyViewModel

class SelectGameVM : ViewModel(), MoneyViewModel {

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SelectGameVM() as T
        }
    }
}