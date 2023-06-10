package com.arcanit.sportbettingapp.make_bet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.arcanit.sportbettingapp.R
import com.arcanit.sportbettingapp.app_top.AppTopHandling
import com.arcanit.sportbettingapp.databinding.BetFragmentBinding
import com.shadowzilot.quiz_app.commons.BaseFragment
import com.shadowzilot.quiz_app.commons.RecreationActivity

class BettingFragment : BaseFragment<BetFragmentBinding>(R.layout.bet_fragment) {

    override val mBinding: BetFragmentBinding by lazy {
        BetFragmentBinding.bind(view ?: throw Exception())
    }
    private val mAppTop by lazy {
        AppTopHandling.Base(
            requireActivity() as RecreationActivity,
            mBinding.appTopBar
        )
    }
    private val mViewModel by viewModels<BettingVM> {
        BettingVM.Factory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAppTop.setMoney(mViewModel.totalMoney())
    }
}