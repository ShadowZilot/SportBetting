package com.arcanit.sportbettingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shadowzilot.quiz_app.commons.RecreationActivity

class MainActivity : AppCompatActivity(), RecreationActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun relaunch() {
        recreate()
    }
}