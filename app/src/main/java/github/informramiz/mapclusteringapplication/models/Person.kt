package github.informramiz.mapclusteringapplication.models

import androidx.annotation.DrawableRes
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import github.informramiz.mapclusteringapplication.R


/**
 * Created by Ramiz Raja on 2019-08-10.
 */
class Person(val location: LatLng,
             val name: String,
             @field:DrawableRes
             val imageRes: Int = R.drawable.placeholder_image): ClusterItem {
    override fun getSnippet(): String? {
        return null
    }

    override fun getTitle(): String? {
        return null
    }

    override fun getPosition(): LatLng {
        return location
    }
}