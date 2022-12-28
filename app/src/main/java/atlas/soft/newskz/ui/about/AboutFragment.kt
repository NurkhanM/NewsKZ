package atlas.soft.newskz.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import atlas.soft.newskz.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        (activity as AppCompatActivity).chipNavigation.visibility = View.VISIBLE

        view.nextCompany.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_aboutFragment_to_myCompanyFragment)
        }

        view.nextProject.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_aboutFragment_to_myProjectFragment)
        }


        return  view
    }


}