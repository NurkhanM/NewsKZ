package atlas.soft.newskz.ui.filter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import atlas.soft.newskz.R
import atlas.soft.newskz.`interface`.IClickListnearHome
import atlas.soft.newskz.`object`.AllProject.GET_SEARCH_REQUEST
import atlas.soft.newskz.`object`.MyUserOBJ.USER_TOKEN
import atlas.soft.newskz.ui.home.TovarAdapterProduct
import atlas.soft.newskz.viewModels.HomeViewModels
import kotlinx.android.synthetic.main.fragment_search_info.view.*

class SearchInfoFragment : Fragment() {

    lateinit var recyclerViewProduct: RecyclerView
    private lateinit var adapterProduct: TovarAdapterProduct
    private lateinit var viewModel: HomeViewModels


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[HomeViewModels::class.java]
        val view = inflater.inflate(R.layout.fragment_search_info, container, false)


        recyclerViewProduct = view.rv_product
//        recyclerViewProduct.addItemDecoration(SpaceItemDecoration(30, false))
        adapterProduct = TovarAdapterProduct(
            object : IClickListnearHome {
                override fun clickListener(baseID: Int) {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_searchInfoFragment_to_homeInfoFragment)
                }

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun clickListenerFavorite(baseID: Int, v: View, boolean: Boolean, pos: Int) {
//                    Navigation.findNavController(view)
//                        .navigate(R.id.action_searchInfoFragment_to_homeFragment)
                }
            })

        recyclerViewProduct.adapter = adapterProduct
        recyclerViewProduct.setHasFixedSize(true)

        view.clickUpdateBackCard.setOnClickListener {
            activity?.onBackPressed()
        }


        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSortProducts("Bearer $USER_TOKEN", GET_SEARCH_REQUEST)
        viewModel.mySortProducts.observe(viewLifecycleOwner) { list ->
            if (list.isSuccessful) {
                list.body()?.data.let { adapterProduct.setList(it!!) }
            }
        }
    }


}