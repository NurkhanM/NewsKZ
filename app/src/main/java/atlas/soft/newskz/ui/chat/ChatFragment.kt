package atlas.soft.newskz.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atlas.soft.newskz.R
import atlas.soft.newskz.`object`.MyUserOBJ.USER_EMAIL_VERIFIED
import kotlinx.android.synthetic.main.fragment_chat.view.*

class ChatFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_chat, container, false)


        if (USER_EMAIL_VERIFIED){
            view.checked_email.visibility = View.GONE
            view.cchat.visibility = View.VISIBLE
        } else {
            view.checked_email.visibility = View.VISIBLE
            view.cchat.visibility = View.GONE
        }


        return view
    }


}