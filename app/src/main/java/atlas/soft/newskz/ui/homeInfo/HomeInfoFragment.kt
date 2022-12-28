package atlas.soft.newskz.ui.homeInfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import atlas.soft.newskz.R
import atlas.soft.newskz.`object`.AllProject
import atlas.soft.newskz.`object`.AllProject.GET_ALL_PRODUCT_ID
import atlas.soft.newskz.viewModels.HomeViewModels
import kotlinx.android.synthetic.main.fragment_home_info.view.*


class HomeInfoFragment : Fragment() {

    private lateinit var viewModel: HomeViewModels

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
        viewModel = ViewModelProvider(this)[HomeViewModels::class.java]
        val view = inflater.inflate(R.layout.fragment_home_info, container, false)



        viewModel.infoNews(GET_ALL_PRODUCT_ID.toString())
        viewModel.myInfoNews.observe(viewLifecycleOwner){ list ->
            if (list.isSuccessful){
                view.textTitle.text = list.body()?.data?.name
                view?.textDescription?.text = list.body()?.data?.description
            }else{
                Toast.makeText(requireContext(), "Sorry", Toast.LENGTH_SHORT).show()
            }
        }



        view.fab_id.setOnClickListener {
            onAddButtonClicked()
        }

        view.ochBackCard.setOnClickListener {
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