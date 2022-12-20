package atlas.soft.newskz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chipNavigation.setOnItemSelectedListener { id ->
            val option = when (id) {
                R.id.homeFragment -> {
                    Navigation.findNavController(this, R.id.nav_host).navigate(R.id.homeFragment)
                }

                R.id.newsFavorite -> {
                    Navigation.findNavController(this, R.id.nav_host).navigate(R.id.favoriteFragment)
                }

                R.id.webSocketFragment -> {
                    Navigation.findNavController(this, R.id.nav_host).navigate(R.id.chatFragment)
                }

                else -> {
                    Navigation.findNavController(this, R.id.nav_host).navigate(R.id.profileFragment)
                }
            }
        }

//        chipNavigation.showBadge(R.id.webSocketFragment, 8)
//        chipNavigation.showBadge(R.id.profileFragment)
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_host)

        if (
            navController.currentDestination!!.id == R.id.favoriteFragment ||
            navController.currentDestination!!.id == R.id.chatFragment ||
            navController.currentDestination!!.id == R.id.profileFragment
        ) {
            chipNavigation.id = 0
            navController.navigate(R.id.homeFragment)
        } else {
            if (navController.currentDestination!!.id != R.id.homeFragment) {
                super.onBackPressed()
            } else {
                val startMain = Intent(Intent.ACTION_MAIN)
                startMain.addCategory(Intent.CATEGORY_HOME)
                startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(startMain)
            }
        }
    }
}