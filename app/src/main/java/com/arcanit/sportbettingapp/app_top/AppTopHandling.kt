package com.arcanit.sportbettingapp.app_top

import android.view.View
import androidx.navigation.findNavController
import com.arcanit.sportbettingapp.databinding.AppTopBinding
import com.shadowzilot.quiz_app.commons.RecreationActivity

interface AppTopHandling {

    fun setMoney(money: Int)

    class Base(
        private val mRecreation: RecreationActivity,
        private val mTopView: AppTopBinding
    ) : AppTopHandling {
        init {
            mTopView.backButton.setOnClickListener {
                mTopView.root.findNavController().popBackStack()
                mRecreation.relaunch()
            }
            val history = mTopView.root.findNavController().backQueue
            mTopView.backButton.visibility = if (history.size > 2) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        override fun setMoney(money: Int) {
            mTopView.moneyInfo.text = money.toString()
        }
    }
}