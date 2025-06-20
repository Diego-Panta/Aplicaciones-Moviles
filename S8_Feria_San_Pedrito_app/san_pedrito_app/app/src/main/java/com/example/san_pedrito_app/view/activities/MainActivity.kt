package com.example.san_pedrito_app.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController

import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.san_pedrito_app.R
import com.example.san_pedrito_app.databinding.ActivityMainBinding
import com.example.san_pedrito_app.view.fragments.FormFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Configurar navegación
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.formFragment,
                R.id.infoFragment,
                R.id.programFragment,
                R.id.termsFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_clear -> {
                // Limpiar formulario
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                    ?.childFragmentManager?.fragments?.firstOrNull()
                    ?.let { fragment ->
                        if (fragment is FormFragment) {
                            fragment.limpiarFormulario()
                        }
                    }
                return true
            }
            R.id.menu_about -> {
                // Mostrar diálogo acerca de
                showAboutDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAboutDialog() {
        android.app.AlertDialog.Builder(this)
            .setTitle("Acerca de")
            .setMessage("Aplicación de registro para el desfile San Pedrito 2023")
            .setPositiveButton("Aceptar", null)
            .show()
    }
}