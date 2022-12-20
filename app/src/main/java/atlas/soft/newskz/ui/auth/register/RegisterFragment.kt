package atlas.soft.newskz.ui.auth.register

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import atlas.soft.newskz.R
import atlas.soft.newskz.models.auth.register.registerErrors.Errors
import atlas.soft.newskz.models.auth.register.registerErrors.RegisterErrors
import atlas.soft.newskz.viewModels.HomeViewModels
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.fragment_register.view.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class RegisterFragment : Fragment() {


    private lateinit var viewModel: HomeViewModels
    private var filePart1: MultipartBody.Part? = null
    var boolean = false

    lateinit var dialog: Dialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[HomeViewModels::class.java]
        val view = inflater.inflate(R.layout.fragment_register, container, false)


        dialog = Dialog(requireContext())


        view.linAvatar.setOnClickListener {
            setNormalSingleButton(view.imageUserAvatar)
        }


        view.btn_registration.setOnClickListener {
            if (view.authEditName.text!!.isNotEmpty() &&
                view.textPhone.text!!.isNotEmpty() &&
                view.authEditEmail.text!!.isNotEmpty() &&
                view.authEditPassword.text!!.isNotEmpty() &&
                view.authEditPassword2.text!!.isNotEmpty()
            ) {
                if (view.authEditPassword.text.toString() == view.authEditPassword2.text.toString()) {
                    if (view.checkboxState.isChecked) {
                        view.scrollConst.visibility = View.GONE
                        view.loafer.visibility = View.VISIBLE
                        val params = HashMap<String, RequestBody>()
                        params["name"] = rb(view.authEditName.text.toString().trim())
                        params["phone"] = rb(view.textPhone.text.toString().trim())
                        params["email"] = rb(view.authEditEmail.text.toString().trim())
                        params["password"] = rb(view.authEditPassword.text.toString().trim())
                        params["password_confirmation"] = rb(view.authEditPassword2.text.toString().trim())

                        viewModel.postRegisterAcc(params, filePart1)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Нужно согласиться с пользовательским соглашением",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                } else {

                    Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT)
                        .show()
                }

            } else {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        viewModel.myRegisterAcc.observe(viewLifecycleOwner) { list ->
            if (list.isSuccessful) {
                Toast.makeText(requireContext(),  "Аккаунт успешно создан", Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
            } else {
                view.scrollConst.visibility = View.VISIBLE
                view.loafer.visibility = View.GONE
                val jsonObj = JSONObject(list.errorBody()!!.charStream().readText())
                val jsonObjErrors = JSONObject(jsonObj.getString("errors"))
                var arrayStringErrors = ""

                if (tryCatch(jsonObjErrors,"name" ) != "" ){
                    arrayStringErrors = "Имя = ${tryCatch(jsonObjErrors,"name" )}"
                }

                if (tryCatch(jsonObjErrors,"phone" ) != "" ){
                    arrayStringErrors += "\n Телефон = ${tryCatch(jsonObjErrors, "phone")}"
                }

                if (tryCatch(jsonObjErrors,"email" ) != "" ){
                    arrayStringErrors += "\n Емейл = ${tryCatch(jsonObjErrors, "email")}"
                }

                if (tryCatch(jsonObjErrors,"password" ) != "" ){
                    arrayStringErrors += "\n Пароль = ${tryCatch(jsonObjErrors, "password")}"
                }

                if (tryCatch(jsonObjErrors,"password_confirmation" ) != "" ){
                    arrayStringErrors += "\n Второй пароль  = ${tryCatch(jsonObjErrors, "password_confirmation")}"
                }

                if (tryCatch(jsonObjErrors,"img" ) != "" ){
                    arrayStringErrors += "\n Фото профиля  = ${tryCatch(jsonObjErrors, "img")}"
                }

                alertDialogCancel(jsonObj.getString("message").toString(), arrayStringErrors )


                }







        }



        view.btn_back_stack.setOnClickListener {
            activity?.onBackPressed()
        }


        return view
    }

    private fun tryCatch(json: JSONObject, string: String) : String {

        var error = ""

        error = try {
            json.getString(string).toString().replace("[\"", "").replace("\"]", "")
        } catch (j: JSONException){
            ""
        }

        return error
    }

    fun rb(value: String): RequestBody {
        return value.toRequestBody("text/plain".toMediaTypeOrNull())
    }

    private fun setNormalSingleButton(image: ImageView) {

        TedImagePicker.with(requireContext())
            .start { uri ->
                showSingleImage(uri, image)
            }
    }

    @SuppressLint("Recycle")
    private fun showSingleImage(uri: Uri, image: ImageView) {

        try {
            image.scaleType = ImageView.ScaleType.CENTER_CROP
            Glide.with(requireContext()).load(uri)
                .thumbnail(Glide.with(requireContext()).load(R.drawable.loader2))
                .into(image)
            sendFileRequest(
                MediaStore.Images.Media.getBitmap(
                    requireContext().contentResolver,
                    uri
                )
            )
            boolean = true


        } catch (e: Exception) {
            e.printStackTrace()
            Glide.with(requireContext()).load(R.drawable.image_add)
                .thumbnail(Glide.with(requireContext()).load(R.drawable.loader2))
                .fitCenter()
                .into(image.imageUserAvatar)
            Toast.makeText(requireContext(), "ErrorImage", Toast.LENGTH_LONG)
                .show()


        }

    }

    private fun sendFileRequest(image: Bitmap) {
        val stream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()

        filePart1 = MultipartBody.Part.createFormData(
            "img",
            "photo",
            byteArray.toRequestBody("image/*".toMediaTypeOrNull(), 0, byteArray.size)
        )

    }


    private fun alertDialogCancel(title: String, descrip: String) {

        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(R.layout.dialog_error_auth)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val buttonYES = dialog.findViewById<ImageView>(R.id.dialog_yes)
        val textTitle = dialog.findViewById<TextView>(R.id.txt_title)
        val textDescrip = dialog.findViewById<TextView>(R.id.txt_descript)
        textTitle.text = title
        textDescrip.text = descrip
        buttonYES.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

}