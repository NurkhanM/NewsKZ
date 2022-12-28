package atlas.soft.newskz.ui.refresh

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import atlas.soft.newskz.MainActivity
import atlas.soft.newskz.R
import atlas.soft.newskz.`object`.MyUserOBJ.USER_STATUS
import atlas.soft.newskz.`object`.MyUserOBJ.USER_TOKEN
import atlas.soft.newskz.`object`.PreferencesOBJ.APP_PREFERENCES
import atlas.soft.newskz.`object`.PreferencesOBJ.KEY_TOKEN
import atlas.soft.newskz.`object`.PreferencesOBJ.KEY_USER_STATUS
import java.io.File

class RefreshActivity : AppCompatActivity() {

    private lateinit var preferencesInfo: SharedPreferences
    private lateinit var preferencesTOKEN: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh)

        clearData()


        preferencesInfo = getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )

        preferencesTOKEN = getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )




        USER_STATUS = false
        USER_TOKEN = ""

        preferencesTOKEN.edit().putString(KEY_TOKEN, USER_TOKEN).apply()
        preferencesInfo.edit().putBoolean(KEY_USER_STATUS, USER_STATUS).apply()

        refreshNextIntent()


    }


    private fun refreshNextIntent() {
//        TEST_ARGS_MAIN_INTENT = "ref"
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun clearData() {
        val cache = cacheDir
        val appDir = cache?.parent?.let { File(it) }
        if (appDir?.exists() == true) {
            val children = appDir.list();
            for (s in children!!) {
                if (!s.equals("lib")) {
                    deleteDir(File(appDir, s))
                }
            }
        }
    }

    private fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children!!) {
                val success = deleteDir(File(dir, i));
                if (!success) {
                    return false
                }
            }
        }

        return dir!!.delete()
    }


}