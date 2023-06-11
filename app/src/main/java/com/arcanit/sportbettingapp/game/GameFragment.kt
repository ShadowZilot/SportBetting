package com.arcanit.sportbettingapp.game

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.animation.addListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.arcanit.sportbettingapp.R
import com.arcanit.sportbettingapp.app_top.AppTopHandling
import com.arcanit.sportbettingapp.databinding.GameFragmentBinding
import com.shadowzilot.quiz_app.commons.BaseFragment
import com.shadowzilot.quiz_app.commons.RecreationActivity
import kotlin.math.abs

class GameFragment : BaseFragment<GameFragmentBinding>(R.layout.game_fragment) {
    override val mBinding: GameFragmentBinding by lazy {
        GameFragmentBinding.bind(view ?: throw Exception())
    }
    private val mViewModel by activityViewModels<GameViewModel> {
        GameViewModel.Factory()
    }
    private val mAppTop by lazy {
        AppTopHandling.Base(
            requireActivity() as RecreationActivity,
            mBinding.appTopBar
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAppTop.setMoney(mViewModel.totalMoney())
        if (savedInstanceState == null) {
            mViewModel.clearTime()
        }
        mBinding.getResultsBtn.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Результаты", Toast.LENGTH_SHORT
            ).show()
        }
        mBinding.yourTeam.text = requireArguments().getString("team_flag")
        mBinding.gameContent.setBackgroundResource(
            when (requireArguments().getInt("game_code")) {
                0 -> R.drawable.soccer_bg
                1 -> R.drawable.tennis_bg
                2 -> R.drawable.hokey_bg
                3 -> R.drawable.box_bg
                else -> R.drawable.soccer_bg
            }
        )
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mViewModel.startCountTimer().collect {
                updateTimer(it)
            }
        }
    }

    private fun updateTimer(time: Long) {
        val minute = time / 60_000L
        val seconds = abs(((time / 60_000L) * 60_000L) - (time / 1000))
        mBinding.timerMinute.text = if (minute < 10) {
            "0$minute"
        } else {
            minute.toString()
        }
        mBinding.timerSeconds.text = if (seconds < 10) {
            "0$seconds"
        } else {
            seconds.toString()
        }
        if (time == 0L) {
            mBinding.scoreText.text = buildString {
                val score = mViewModel.randomScore()
                append(score.first)
                append(" : ")
                append(score.second)
            }
            val animator = AnimatorSet()
            animator.playTogether(
                ObjectAnimator.ofFloat(mBinding.scoreText, "alpha", 0f, 1f),
                ObjectAnimator.ofFloat(mBinding.scoreLabel, "alpha", 0f, 1f),
                ObjectAnimator.ofFloat(
                    mBinding.getResultsBtn,
                    "alpha", 0f, 1f
                )
            )
            animator.addListener(onEnd = {
                mBinding.getResultsBtn.isEnabled = true
            })
            animator.duration = 250
            animator.start()
        }
    }
}