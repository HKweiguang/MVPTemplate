package cn.shimmer.mvptemplate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LauncherActiviy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.actionStart(this)
        finish()
    }
}