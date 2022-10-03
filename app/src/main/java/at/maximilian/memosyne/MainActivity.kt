package at.maximilian.memosyne

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager
import at.maximilian.memosyne.databinding.ActivityMainBinding

/**
 * Main Activity that houses all the fragments
 */
class MainActivity : AppCompatActivity(), OnSharedPreferenceChangeListener {

    private var _appBarConfiguration: AppBarConfiguration? = null
    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!
    private val appBarConfiguration get() = _appBarConfiguration!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Code from: https://stackoverflow.com/a/60597670/17947697
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController

        _appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.registerOnSharedPreferenceChangeListener(this)
        AppCompatDelegate.setDefaultNightMode(
            prefs.getString("theme", "-1")?.toInt() ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key.equals("theme")) {
            val setting = sharedPreferences?.getString(key, "-1") ?: return
            AppCompatDelegate.setDefaultNightMode(setting.toInt())
        }
    }
}