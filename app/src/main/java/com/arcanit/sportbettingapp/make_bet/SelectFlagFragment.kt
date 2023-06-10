package com.arcanit.sportbettingapp.make_bet

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.arcanit.sportbettingapp.R
import com.arcanit.sportbettingapp.app_top.AppTopHandling
import com.arcanit.sportbettingapp.databinding.FlagsFragmentBinding
import com.shadowzilot.quiz_app.commons.BaseFragment
import com.shadowzilot.quiz_app.commons.RecreationActivity

class SelectFlagFragment : BaseFragment<FlagsFragmentBinding>(R.layout.flags_fragment) {
    override val mBinding: FlagsFragmentBinding by lazy {
        FlagsFragmentBinding.bind(view ?: throw Exception())
    }
    private val mViewModel by activityViewModels<BettingVM> {
        BettingVM.Factory()
    }
    private val mAppTop by lazy {
        AppTopHandling.Base(
            requireActivity() as RecreationActivity,
            mBinding.appTopBar
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAppTop.setMoney(mViewModel.totalMoney())
        for (i in 0 until mBinding.flagsContainer.childCount) {
            (mBinding.flagsContainer.getChildAt(i) as ViewGroup).getChildAt(0)
                .setOnClickListener {
                    mViewModel.selectYourTeam((it as TextView).text as String)
                    findNavController().popBackStack()
                    (requireActivity() as RecreationActivity).relaunch()
                }
        }
    }
}