package com.arcanit.sportbettingapp.commons

import com.arcanit.sportbettingapp.total_money.TotalMoney

interface MoneyViewModel {

    fun totalMoney() : Int {
        return TotalMoney.Base.Instance.invoke().moneyAmount()
    }
}