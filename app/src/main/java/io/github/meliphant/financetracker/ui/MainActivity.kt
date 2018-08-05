package io.github.meliphant.financetracker.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.ui.wallets.WalletsFragment

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, WalletsFragment())
                .commitAllowingStateLoss()
    }

}
