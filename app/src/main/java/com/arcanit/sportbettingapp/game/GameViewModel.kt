package com.arcanit.sportbettingapp.game

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.arcanit.sportbettingapp.commons.MoneyViewModel
import com.arcanit.sportbettingapp.total_money.TotalMoney
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val sCountTime = 60_000L

class GameViewModel : ViewModel(), MoneyViewModel {
    private val mTimer = object : CountDownTimer(sCountTime, 1000L) {
        override fun onTick(millisUntilFinished: Long) {
            viewModelScope.launch {
                mTimeFlow.emit(millisUntilFinished)
            }
        }

        override fun onFinish() {
            viewModelScope.launch {
                mTimeFlow.emit(0L)
            }
        }
    }
    private var mScore = Pair(0, 0)
    private val mTimeFlow = MutableStateFlow(sCountTime)

    fun clearTime() {
        viewModelScope.launch {
            mTimeFlow.emit(sCountTime)
        }
    }

    fun startCountTimer() : Flow<Long> {
        mTimer.start()
        return mTimeFlow
    }

    fun checkVictory(bet: Int) : Boolean {
        return if (mScore.first > mScore.second) {
            false
        } else {
            TotalMoney.Base.Instance().writeMoney(
                TotalMoney.Base.Instance().moneyAmount() + (bet * 2)
            )
            true
        }
    }

    fun randomScore() : Pair<Int, Int> {
        var enemyScore = (0..10).random()
        var yourScore = (0..10).random()
        while (enemyScore == yourScore) {
            enemyScore = (0..10).random()
            yourScore = (0..10).random()
        }
        mScore = Pair(enemyScore, yourScore)
        return mScore
    }

    class Factory : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GameViewModel() as T
        }
    }
}