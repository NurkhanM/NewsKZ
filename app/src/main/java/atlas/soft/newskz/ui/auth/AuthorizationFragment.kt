package atlas.soft.newskz.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import atlas.soft.newskz.R
import atlas.soft.newskz.`object`.MyUserOBJ.USER_EMAIL_VERIFIED
import atlas.soft.newskz.`object`.MyUserOBJ.USER_STATUS
import atlas.soft.newskz.`object`.MyUserOBJ.USER_TOKEN
import atlas.soft.newskz.`object`.PreferencesOBJ.APP_PREFERENCES
import atlas.soft.newskz.`object`.PreferencesOBJ.KEY_TOKEN
import atlas.soft.newskz.`object`.PreferencesOBJ.KEY_USER_STATUS
import atlas.soft.newskz.viewModels.HomeViewModels
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_authorization.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class AuthorizationFragment : Fragment() {


    private lateinit var preferencesTOKEN: SharedPreferences
    private lateinit var preferencesUSERSTATUS: SharedPreferences

    private lateinit var viewModel: HomeViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewModel = ViewModelProvider(this)[HomeViewModels::class.java]
        val view = inflater.inflate(R.layout.fragment_authorization, container, false)


        preferencesTOKEN = (activity as AppCompatActivity).getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )

        preferencesUSERSTATUS = (activity as AppCompatActivity).getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )


        view.create_account.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_authorizationFragment_to_registerFragment)
        }

        
        
        view.btn_auth.setOnClickListener {
            if (view.authEditEmail.text!!.isNotEmpty() &&
                view.authEditPassword.text!!.isNotEmpty()
            ) {
                view.constraintLayoutAuth.visibility = View.GONE
                view.gif_aut.visibility = View.VISIBLE
                view.textWelcome.visibility = View.GONE

                val paramObjectt = JsonObject()
                paramObjectt.addProperty("email", view.authEditEmail.text.toString().trim())
                paramObjectt.addProperty("password", view.authEditPassword.text.toString().trim())
                if (view.chkb_remember.isChecked){
                    paramObjectt.addProperty("remember", "1")
                }else{
                    paramObjectt.addProperty("remember", "0")
                }

                view.btn_auth.backgroundTintList =
                    resources.getColorStateList(R.color.black_txt4)

                viewModel.postLogin(paramObjectt)


            } else {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT)
                    .show()
            }

        }



        viewModel.myLogin.observe(viewLifecycleOwner) { list ->
            if (list.isSuccessful) {
                view.constraintLayoutAuth.visibility = View.GONE
                view.gif_aut.visibility = View.GONE
                view.textWelcome.visibility = View.VISIBLE
                USER_STATUS = true

                if (list.body()?.user?.email_verified_at != null){
                    USER_EMAIL_VERIFIED = true
                }

                view.gif_aut.visibility = View.GONE

                USER_TOKEN = list.body()?.token.toString()

                CoroutineScope(Dispatchers.Main).launch {
                    delay(2000)
                    Navigation.findNavController(view)
                        .navigate(R.id.action_authorizationFragment_to_aboutFragment)
                }

                preferencesTOKEN.edit().putString(KEY_TOKEN, USER_TOKEN).apply()
                preferencesUSERSTATUS.edit().putBoolean(KEY_USER_STATUS, USER_STATUS).apply()


            } else {
                view.constraintLayoutAuth.visibility = View.VISIBLE
                view.gif_aut.visibility = View.GONE
                view.textWelcome.visibility = View.GONE

                try {
                    val jsonObj = JSONObject(list.errorBody()!!.charStream().readText())

                    Toast.makeText(requireContext(),  jsonObj.getString("message").toString(), Toast.LENGTH_SHORT).show()

                } catch (e: RuntimeException) {
                    Toast.makeText(requireContext(),  "Загрузка сервер обновляется", Toast.LENGTH_SHORT).show()
                }catch (e: JSONException) {
                    Toast.makeText(requireContext(),  "Загрузка сервер обновляется", Toast.LENGTH_SHORT).show()
                }

            }
        }



        return  view

    }


}