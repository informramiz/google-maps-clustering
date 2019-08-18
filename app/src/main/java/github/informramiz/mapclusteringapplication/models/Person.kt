package github.informramiz.mapclusteringapplication.models

import androidx.recyclerview.widget.DiffUtil
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem


/**
 * Created by Ramiz Raja on 2019-08-10.
 */
data class Person(val location: LatLng,
             val name: String,
             val imageUrl: String): ClusterItem {
    companion object {
        val DIFF_ITEM_CALLBACK = object: DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem == newItem
            }

        }
    }
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