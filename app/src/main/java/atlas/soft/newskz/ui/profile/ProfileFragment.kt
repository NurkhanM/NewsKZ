package atlas.soft.newskz.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import atlas.soft.newskz.R
import atlas.soft.newskz.ui.refresh.RefreshActivity
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        view.nextMyProfile.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_myProfileFragment)
        }

        view.nextExitUser.setOnClickListener {
            val intent = Intent(requireActivity(), RefreshActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.fab_rotate_open, R.anim.fab_rotate_close)
            activity?.finish()
        }

        return  view
    }

}