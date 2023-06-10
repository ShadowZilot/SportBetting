package com.arcanit.sportbettingapp.select_game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arcanit.sportbettingapp.R
import com.arcanit.sportbettingapp.app_top.AppTopHandling
import com.arcanit.sportbettingapp.databinding.StartFragmentBinding
import com.shadowzilot.quiz_app.commons.BaseFragment
import com.shadowzilot.quiz_app.commons.RecreationActivity

class SelectGameFragment : BaseFragment<StartFragmentBinding>(R.layout.start_fragment) {
    override val mBinding by lazy {
        StartFragmentBinding.bind(view ?: throw Exception())
    }
    private val mAppTop by lazy {
        AppTopHandling.Base(
            requireActivity() as RecreationActivity,
            mBinding.appTopBar)
    }
    private val mViewModel by viewModels<SelectGameVM> {
        SelectGameVM.Factory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAppTop.setMoney(mViewModel.totalMoney())
        mBinding.playSoccer.setOnClickListener {
            navigateToBet(0)
        }
        mBinding.playTennis.setOnClickListener {
            navigateToBet(1)
        }
        mBinding.playHokey.setOnClickListener {
            navigateToBet(2)
        }
        mBinding.playBox.setOnClickListener {
            navigateToBet(3)
        }
    }

    private fun navigateToBet(codeGame: Int) {
        findNavController().navigate(
            R.id.action_selectGameFragment_to_bettingFragment,
            Bundle().apply {
                putInt("game_code", codeGame)
            }
        )
    }
}