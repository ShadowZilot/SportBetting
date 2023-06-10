package com.arcanit.sportbettingapp.app_top

import com.arcanit.sportbettingapp.databinding.AppTopBinding

interface AppTopHandling {

    fun setMoney(money: Int)

    class Base(
        private val mTopView: AppTopBinding
    ) : AppTopHandling {

        override fun setMoney(money: Int) {
            mTopView.moneyInfo.text = money.toString()
        }
    }
}