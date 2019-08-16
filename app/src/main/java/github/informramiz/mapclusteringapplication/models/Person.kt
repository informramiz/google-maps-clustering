package github.informramiz.mapclusteringapplication.models

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem


/**
 * Created by Ramiz Raja on 2019-08-10.
 */
class Person(private val name: String,
             private val position: LatLng): ClusterItem {
    override fun getSnippet(): String? {
        return null
    }

    override fun getTitle(): String? {
        return null
    }

    override fun getPosition(): LatLng {
        return position
    }
}