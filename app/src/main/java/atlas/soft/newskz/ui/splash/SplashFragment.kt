package atlas.soft.newskz.ui.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import atlas.soft.newskz.R
import atlas.soft.newskz.`object`.MyUserOBJ.USER_TOKEN
import atlas.soft.newskz.`object`.PreferencesOBJ.APP_PREFERENCES
import atlas.soft.newskz.`object`.PreferencesOBJ.KEY_TOKEN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private lateinit var preferencesTOKEN: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        preferencesTOKEN = (activity as AppCompatActivity).getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )

        USER_TOKEN = preferencesTOKEN.getString(KEY_TOKEN, "").toString()

        fullScreen()

        if (USER_TOKEN != ""){
            CoroutineScope(Dispatchers.Main).launch {
                delay(2000)
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_aboutFragment)
            }
        }else{
            CoroutineScope(Dispatchers.Main).launch {
                delay(2000)
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_authorizationFragment)
            }
        }



        return view
    }

    @SuppressLint("ObsoleteSdkInt")
    fun fullScreen() {

        val uiOptions = (activity as AppCompatActivity).window.decorView.systemUiVisibility
        var newUiOptions = uiOptions

        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
        (activity as AppCompatActivity).window.decorView.systemUiVisibility = newUiOptions
    }
}