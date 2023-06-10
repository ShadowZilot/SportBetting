package com.arcanit.sportbettingapp.make_bet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arcanit.sportbettingapp.R
import com.arcanit.sportbettingapp.app_top.AppTopHandling
import com.arcanit.sportbettingapp.databinding.BetFragmentBinding
import com.shadowzilot.quiz_app.commons.BaseFragment
import com.shadowzilot.quiz_app.commons.RecreationActivity
import java.lang.NumberFormatException

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
    private val mViewModel by activityViewModels<BettingVM> {
        BettingVM.Factory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAppTop.setMoney(mViewModel.totalMoney())
        mBinding.selectTeamBtn.setOnClickListener {
            findNavController().navigate(R.id.action_bettingFragment_to_selectFlagFragment)
        }
        mBinding.startGame.isEnabled = mViewModel.validateBet(
            try {
                mBinding.bettingInput.text?.toString()?.toInt() ?: 0
            } catch (e: NumberFormatException) {
                0
            }
        )
        mBinding.bettingInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                mBinding.startGame.isEnabled = mViewModel.validateBet(
                    try {
                        s?.toString()?.toInt() ?: 0
                    } catch (e: NumberFormatException) {
                        0
                    }
                )
            }
        })
    }

    override fun onStart() {
        super.onStart()
        val selectedFlag = mViewModel.yourTeamFlag()
        if (selectedFlag.isEmpty()) {
            mBinding.yourTeam.setText(R.string.your_team_label)
            mBinding.yourTeam.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        } else {
            mBinding.yourTeam.text = selectedFlag
            mBinding.yourTeam.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60f)
        }
    }
}