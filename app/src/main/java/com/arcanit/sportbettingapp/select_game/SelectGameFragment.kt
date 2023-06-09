package com.arcanit.sportbettingapp.select_game

import com.arcanit.sportbettingapp.R
import com.arcanit.sportbettingapp.databinding.StartFragmentBinding
import com.shadowzilot.quiz_app.commons.BaseFragment

class SelectGameFragment : BaseFragment<StartFragmentBinding>(R.layout.start_fragment) {
    override val mBinding by lazy {
        StartFragmentBinding.bind(view ?: throw Exception())
    }
}