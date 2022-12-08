package atlas.soft.newskz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
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
}