package project.dheeraj.cinewatch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import project.dheeraj.cinewatch.R
import project.dheeraj.cinewatch.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler().postDelayed({
            MainActivity.start(this@SplashScreenActivity)
        }, 2000)


    }
}