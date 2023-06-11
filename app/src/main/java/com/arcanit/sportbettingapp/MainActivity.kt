package com.arcanit.sportbettingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arcanit.sportbettingapp.total_money.TotalMoney
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.shadowzilot.quiz_app.commons.RecreationActivity

class MainActivity : AppCompatActivity(), RecreationActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        TotalMoney.Base.Instance.create(this)
        setContentView(R.layout.activity_main)
    }

    override fun relaunch() {
        recreate()
    }
}