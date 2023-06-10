package com.arcanit.sportbettingapp.select_game

import android.os.Bundle
import android.view.View
import com.arcanit.sportbettingapp.R
import com.arcanit.sportbettingapp.app_top.AppTopHandling
import com.arcanit.sportbettingapp.databinding.StartFragmentBinding
import com.shadowzilot.quiz_app.commons.BaseFragment

class SelectGameFragment : BaseFragment<StartFragmentBinding>(R.layout.start_fragment) {
    override val mBinding by lazy {
        StartFragmentBinding.bind(view ?: throw Exception())
    }
    private val mAppTop by lazy {
        AppTopHandling.Base(mBinding.appTopBar)
    }
    private val mViewModel by lazy {
        SelectGameVM()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAppTop.setMoney(mViewModel.totalMoney())
    }
}