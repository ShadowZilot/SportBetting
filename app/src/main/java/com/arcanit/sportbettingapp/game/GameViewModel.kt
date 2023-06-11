package com.arcanit.sportbettingapp.game

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.arcanit.sportbettingapp.commons.MoneyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GameViewModel : ViewModel(), MoneyViewModel {
    private val mTimer = object : CountDownTimer(60_000L, 1000L) {
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
    private val mTimeFlow = MutableStateFlow(60_000L)

    fun clearTime() {
        viewModelScope.launch {
            mTimeFlow.emit(60_000L)
        }
    }

    fun startCountTimer() : Flow<Long> {
        mTimer.start()
        return mTimeFlow
    }

    fun randomScore() : Pair<Int, Int> {
        var enemyScore = (0..10).random()
        var yourScore = (0..10).random()
        while (enemyScore == yourScore) {
            enemyScore = (0..10).random()
            yourScore = (0..10).random()
        }
        return Pair(enemyScore, yourScore)
    }

    class Factory : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GameViewModel() as T
        }
    }
}