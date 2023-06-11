package com.arcanit.sportbettingapp.game.results

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.arcanit.sportbettingapp.R
import com.arcanit.sportbettingapp.app_top.AppTopHandling
import com.arcanit.sportbettingapp.commons.navigateWithoutBack
import com.arcanit.sportbettingapp.databinding.ResultsFragmentBinding
import com.arcanit.sportbettingapp.game.GameViewModel
import com.shadowzilot.quiz_app.commons.BaseFragment
import com.shadowzilot.quiz_app.commons.RecreationActivity

class ResultFragment : BaseFragment<ResultsFragmentBinding>(R.layout.results_fragment) {
    override val mBinding: ResultsFragmentBinding by lazy {
        ResultsFragmentBinding.bind(view ?: throw Exception())
    }
    private val mAppTop by lazy {
        AppTopHandling.Base(
            requireActivity() as RecreationActivity,
            mBinding.appTopBar
        )
    }
    private val mViewModel by activityViewModels<GameViewModel> {
        GameViewModel.Factory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val betAmount = requireArguments().getInt("bet")
        mBinding.toMainMenu.setOnClickListener {
            findNavController().clearBackStack(
                R.id.action_resultFragment_to_selectGameFragment
            )
            findNavController().navigateWithoutBack(
                R.id.action_resultFragment_to_selectGameFragment
            )
        }
        mBinding.getGiftBtn.setOnClickListener {
            findNavController().clearBackStack(
                R.id.action_resultFragment_to_giftFragment
            )
            findNavController().navigateWithoutBack(
                R.id.action_resultFragment_to_giftFragment
            )
        }
        if (mViewModel.checkVictory(betAmount)) {
            mBinding.resultLabel.text = resources.getString(R.string.results_score,
                betAmount * 2)
            mBinding.failResults.visibility = View.GONE
            mBinding.successResults.visibility = View.VISIBLE
            mBinding.getGiftBtn.visibility = View.VISIBLE
        } else {
            mBinding.failResults.visibility = View.VISIBLE
            mBinding.successResults.visibility = View.GONE
            mBinding.getGiftBtn.visibility = View.GONE
        }
        mAppTop.setMoney(mViewModel.totalMoney())
    }
}