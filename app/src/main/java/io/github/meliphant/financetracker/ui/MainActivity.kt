package io.github.meliphant.financetracker.ui

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import io.github.meliphant.financetracker.R
import io.github.meliphant.financetracker.ui.diagram.DiagramFragment
import io.github.meliphant.financetracker.ui.mywallets.MyWalletsFragment
import io.github.meliphant.financetracker.ui.periodical.PeriodicalOperationsFragment
import io.github.meliphant.financetracker.ui.settings.SettingsFragment
import io.github.meliphant.financetracker.ui.wallets.WalletsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        bottomNavigation.addItem(AHBottomNavigationItem("home", R.drawable.nav_home))
        bottomNavigation.addItem(AHBottomNavigationItem("periodical", R.drawable.nav_periodical))
        bottomNavigation.addItem(AHBottomNavigationItem("diagram", R.drawable.nav_diagram))
        bottomNavigation.addItem(AHBottomNavigationItem("settings", R.drawable.nav_settings))


        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            when(position) {
                0 ->  supportFragmentManager.beginTransaction().replace(R.id.fl_main, WalletsFragment())
                        .commitAllowingStateLoss()
                1 -> supportFragmentManager.beginTransaction().replace(R.id.fl_main, PeriodicalOperationsFragment())
                        .commitAllowingStateLoss()
                2 ->  supportFragmentManager.beginTransaction().replace(R.id.fl_main, MyWalletsFragment())
                            .commitAllowingStateLoss()
                3 -> supportFragmentManager.beginTransaction().replace(R.id.fl_main, DiagramFragment())
                        .commitAllowingStateLoss()

            }
            true
        }

//        bottomNavigation.titleState =
        bottomNavigation.accentColor = Color.BLACK
        bottomNavigation.currentItem = 0
        bottomNavigation.isBehaviorTranslationEnabled = true

    }

}
