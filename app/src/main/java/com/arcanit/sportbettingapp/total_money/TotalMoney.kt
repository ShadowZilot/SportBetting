package com.arcanit.sportbettingapp.total_money

import android.content.Context

interface TotalMoney {

    fun moneyAmount() : Int

    fun writeMoney(money: Int)

    class Base private constructor(
        context: Context
    ) : TotalMoney {
        private val mData = context.getSharedPreferences("money",
            Context.MODE_PRIVATE)

        override fun moneyAmount(): Int {
            return mData.getInt("total_money", 100)
        }

        override fun writeMoney(money: Int) {
            mData.edit()
                .putInt("total_money", money)
                .apply()
        }

        object Instance {
            private var mInstance: TotalMoney? = null

            fun create(context: Context) {
                if (mInstance == null) {
                    mInstance = Base(context)
                }
            }

            operator fun invoke() : TotalMoney {
                return mInstance ?: throw Exception()
            }
        }
    }
}