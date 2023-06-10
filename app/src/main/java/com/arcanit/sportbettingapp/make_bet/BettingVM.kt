package com.arcanit.sportbettingapp.make_bet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arcanit.sportbettingapp.commons.MoneyViewModel
import com.arcanit.sportbettingapp.total_money.TotalMoney

class BettingVM : ViewModel(), MoneyViewModel {
    private var mFlag: String = ""

    fun selectYourTeam(flag: String) {
        mFlag = flag
    }

    fun yourTeamFlag() : String = mFlag

    fun validateBet(betAmount: Int) : Boolean {
        return if (betAmount > TotalMoney.Base.Instance().moneyAmount()) {
            false
        } else betAmount >= 100
    }

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BettingVM() as T
        }
    }
}