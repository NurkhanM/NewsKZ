package atlas.soft.newskz.ui.profile.myProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atlas.soft.newskz.R
import kotlinx.android.synthetic.main.fragment_my_profile.view.*

class MyProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)



        view.ochBackCard.setOnClickListener {
            activity?.onBackPressed()
        }
        return view
    }

}