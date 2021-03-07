package com.roshastudio.roshastu.view

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.postDelayed
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.infideap.drawerbehavior.AdvanceDrawerLayout
import com.roshastudio.roshastu.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerlayout: AdvanceDrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var drawertogle: ActionBarDrawerToggle
    private lateinit var listener: NavController.OnDestinationChangedListener
    private lateinit var navHostFragment:NavHostFragment
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        drawerlayout = drawer_layout


        }

    fun setToolbar(toolbar: Toolbar?) {

        if(toolbar != null) {
            setSupportActionBar(toolbar);

                drawertogle = ActionBarDrawerToggle(this@MainActivity, drawerlayout, R.string.open, R.string.close)
                drawerlayout.addDrawerListener(drawertogle)
                drawertogle.syncState()

                appBarConfiguration = AppBarConfiguration(
                    setOf(
                        R.id.fragmentHome,
                        R.id.orderFragment,
                        R.id.productListFragment,
                        R.id.profileFragment
                    ), drawerlayout
                )

            NavigationUI.setupActionBarWithNavController(this@MainActivity,navController,appBarConfiguration)
            NavigationUI.setupWithNavController(navigationView, navController)

            drawerlayout.setViewScale(GravityCompat.END, 0.85f) //set height scale for main view (0f to 1f)
            drawerlayout.setRadius(GravityCompat.END, 30f)
            drawerlayout.setViewScrimColor(GravityCompat.END, Color.TRANSPARENT); //set drawer overlay coloe (color)
            drawerlayout.setViewElevation(GravityCompat.END, 30f); //set main view elevation when drawer open (dimension)
            drawerlayout.closeDrawer(GravityCompat.START)

        } else {
                drawertogle = ActionBarDrawerToggle(this@MainActivity, null, R.string.open, R.string.close)
                drawerlayout.closeDrawer(GravityCompat.START)

                drawerlayout.addDrawerListener(drawertogle);

        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        drawertogle = ActionBarDrawerToggle(this, drawerlayout, R.string.open, R.string.close)
        drawerlayout.addDrawerListener(drawertogle)
        drawertogle.syncState()
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



}
