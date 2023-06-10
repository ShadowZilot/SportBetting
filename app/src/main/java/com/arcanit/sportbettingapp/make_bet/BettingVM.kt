package com.arcanit.sportbettingapp.make_bet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arcanit.sportbettingapp.commons.MoneyViewModel

class BettingVM : ViewModel(), MoneyViewModel {

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BettingVM() as T
        }
    }
}