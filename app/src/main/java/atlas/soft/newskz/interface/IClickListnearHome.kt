package atlas.soft.newskz.`interface`

import android.view.View

interface IClickListnearHome {
    fun clickListener(baseID: Int)
    fun clickListenerFavorite(baseID: Int, v: View, boolean: Boolean, pos: Int)
}