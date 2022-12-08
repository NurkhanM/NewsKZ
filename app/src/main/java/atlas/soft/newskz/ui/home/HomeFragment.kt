package atlas.soft.newskz.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import atlas.soft.newskz.R
import atlas.soft.newskz.`interface`.IClickListnearHome
import atlas.soft.newskz.viewModels.HomeViewModels
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    lateinit var recyclerViewProduct: RecyclerView
    private lateinit var adapterProduct: TovarAdapterProduct
    private lateinit var viewModel: HomeViewModels

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[HomeViewModels::class.java]
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        (activity as AppCompatActivity).chipNavigation.visibility = View.VISIBLE

        view.swipeRefreshLayout.setOnRefreshListener {
            recyclerViewProduct.removeAllViewsInLayout()
            onResume()
            adapterProduct.notifyDataSetChanged()
            view.swipeRefreshLayout.isRefreshing = false
        }


        recyclerViewProduct = view.rv_home
//        recyclerViewProduct.addItemDecoration(SpaceItemDecoration(30, false))
        adapterProduct = TovarAdapterProduct(
            object : IClickListnearHome {
                override fun clickListener(baseID: Int) {
//                    if (!USER_STATUS) {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_homeFragment_to_homeInfoFragment)
//                    } else {
//                        ALL_ID_PRODUCTS = baseID
//                        Navigation.findNavController(view)
//                            .navigate(R.id.action_homeFragment_to_homeInfoFragment)
//                    }
                }

                override fun clickListenerFavorite(baseID: Int, v: View, boolean: Boolean, pos: Int) {
//                    if (!USER_STATUS) {
//                        Navigation.findNavController(view)
//                            .navigate(R.id.action_homeFragment_to_authorizationFragment)
//                    } else {
//                        if (!boolean) {
//                            viewModel.addFavorite("Bearer $TOKEN_USER", baseID)
//                            v.img_favorite?.setImageDrawable(
//                                requireContext().resources.getDrawable(
//                                    R.drawable.ic_favorite2
//                                )
//                            )
//                        } else {
//                            viewModel.deleteFavorite("Bearer $TOKEN_USER", baseID)
//                            v.img_favorite?.setImageDrawable(
//                                requireContext().resources.getDrawable(
//                                    R.drawable.ic_favorite
//                                )
//                            )
//                        }
//                    }
                }
            })

        recyclerViewProduct.adapter = adapterProduct
        recyclerViewProduct.setHasFixedSize(true)


        return view
    }


    override fun onResume() {
        super.onResume()

        viewModel.allNews()
        viewModel.myAllNews.observe(viewLifecycleOwner) { list ->
            if (list.isSuccessful) {
                list.body()?.data?.let { adapterProduct.setList(it) }
            }
        }
    }
}