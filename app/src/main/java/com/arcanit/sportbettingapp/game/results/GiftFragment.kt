package com.arcanit.sportbettingapp.game.results

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arcanit.sportbettingapp.R
import com.arcanit.sportbettingapp.app_top.AppTopHandling
import com.arcanit.sportbettingapp.commons.navigateWithoutBack
import com.arcanit.sportbettingapp.databinding.GiftFragmentBinding
import com.arcanit.sportbettingapp.select_game.SelectGameVM
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.shadowzilot.quiz_app.commons.BaseFragment
import com.shadowzilot.quiz_app.commons.RecreationActivity

class GiftFragment : BaseFragment<GiftFragmentBinding>(R.layout.gift_fragment) {
    override val mBinding: GiftFragmentBinding by lazy {
        GiftFragmentBinding.bind(view ?: throw Exception())
    }
    private val mAppTop by lazy {
        AppTopHandling.Base(
            requireActivity() as RecreationActivity,
            mBinding.appTopBar
        )
    }
    private val mViewModel by viewModels<SelectGameVM> {
        SelectGameVM.Factory()
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAppTop.setMoney(mViewModel.totalMoney())
        mBinding.giftView.settings.javaScriptEnabled = true
        Firebase.remoteConfig.setDefaultsAsync(R.xml.config_values)
        Firebase.remoteConfig.fetchAndActivate()
            .addOnCompleteListener {
                mBinding.giftView.loadUrl(
                    Firebase.remoteConfig.getString("gift_link")
                )
            }
            .addOnCanceledListener {
                findNavController().navigateWithoutBack(
                    R.id.action_giftFragment_to_selectGameFragment
                )
                Toast.makeText(
                    requireContext(),
                    R.string.gift_loading_error, Toast.LENGTH_LONG
                ).show()
            }
    }
}