package com.gmail.zyanyasanchezv.elementosuiviews

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.gmail.zyanyasanchezv.elementosuiviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment_content_main
            ) as NavHostFragment

        val navController = navHostFragment.navController

        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.HomeFragment))

        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val navController =
            findNavController(R.id.nav_host_fragment_content_main)

        return when (item.itemId) {

            R.id.HomeFragment -> {
                navController.popBackStack(
                    R.id.HomeFragment,
                    false
                )
                true
            }

            R.id.EntradaTextoFragment,
            R.id.BotonesAccionesFragment,
            R.id.SeleccionFragment,
            R.id.ListasColeccionesFragment,
            R.id.InformacionFragment,
            R.id.ContenedoresFragment -> {

                navController.navigate(item.itemId) {
                    popUpTo(R.id.HomeFragment)
                    launchSingleTop = true
                }

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController =
            findNavController(R.id.nav_host_fragment_content_main)

        return navController.navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }
}