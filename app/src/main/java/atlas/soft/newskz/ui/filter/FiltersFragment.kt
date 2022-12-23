package atlas.soft.newskz.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.Navigation
import atlas.soft.newskz.R
import atlas.soft.newskz.`object`.AllProject.GET_SEARCH_REQUEST
import kotlinx.android.synthetic.main.fragment_filters.view.*

class FiltersFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_filters, container, false)


        view.btnSortSearch.setOnClickListener {
            metDateSearch(view)
            Navigation.findNavController(view)
                .navigate(R.id.action_filtersFragment_to_searchInfoFragment)
        }

        view.edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                metDateSearch(view)
                Navigation.findNavController(view)
                    .navigate(R.id.action_filtersFragment_to_searchInfoFragment)
            }
            true
        }


        view.radioDefault.setOnClickListener {
            view.radioDefault.isChecked = true
            view.radioStarted.isChecked = false
            view.radioSmall.isChecked = false
            view.radioBig.isChecked = false
        }
        view.radioStarted.setOnClickListener {
            view.radioDefault.isChecked = false
            view.radioStarted.isChecked = true
            view.radioSmall.isChecked = false
            view.radioBig.isChecked = false
        }
        view.radioSmall.setOnClickListener {
            view.radioDefault.isChecked = false
            view.radioStarted.isChecked = false
            view.radioSmall.isChecked = true
            view.radioBig.isChecked = false
        }
        view.radioBig.setOnClickListener {
            view.radioDefault.isChecked = false
            view.radioStarted.isChecked = false
            view.radioSmall.isChecked = false
            view.radioBig.isChecked = true
        }

        view.clickUpdateBackCard.setOnClickListener {
            activity?.onBackPressed()
        }
        return view
    }

    private fun metDateSearch(v: View) {
        GET_SEARCH_REQUEST ["name"] =  v.edtSearch.text.toString()
//        GET_SEARCH_REQUEST ["category"] = "1"

    }



}