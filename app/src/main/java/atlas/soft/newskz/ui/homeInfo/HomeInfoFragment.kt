package atlas.soft.newskz.ui.homeInfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import atlas.soft.newskz.R
import kotlinx.android.synthetic.main.fragment_home_info.view.*


class HomeInfoFragment : Fragment() {



    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.fab_rotate_open
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.fab_rotate_close
        )
    }
    private val rotateFrom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.fab_from_bottom
        )
    }
    private val rotateTo: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.fab_to_bottom
        )
    }
    private var clicked = false

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_info, container, false)

        (activity as AppCompatActivity).setSupportActionBar(view.newsToolbar)
        (activity as AppCompatActivity).title = "Welt Floxy 1.0 V Whiteasdas"

        view.fab_id.setOnClickListener {
            onAddButtonClicked()
        }

        view.fab_back.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            view?.floatingActionButtonFavorite?.visibility = View.VISIBLE
            view?.floatingActionButtonShare?.visibility = View.VISIBLE
        } else {
            view?.floatingActionButtonShare?.visibility = View.INVISIBLE
            view?.floatingActionButtonFavorite?.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            view?.floatingActionButtonFavorite?.startAnimation(rotateFrom)
            view?.floatingActionButtonShare?.startAnimation(rotateFrom)
            view?.fab_id?.startAnimation(rotateOpen)
        } else {
            view?.floatingActionButtonFavorite?.startAnimation(rotateTo)
            view?.floatingActionButtonShare?.startAnimation(rotateTo)
            view?.fab_id?.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean) {
        if (!clicked) {
            view?.floatingActionButtonFavorite?.isClickable = true
            view?.floatingActionButtonShare?.isClickable = true
        } else {
            view?.floatingActionButtonFavorite?.isClickable = false
            view?.floatingActionButtonShare?.isClickable = false
        }
    }

}