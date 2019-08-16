package github.informramiz.mapclusteringapplication.models

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Picasso
import github.informramiz.mapclusteringapplication.R
import github.informramiz.mapclusteringapplication.customviews.WindowInfoView


/**
 * Created by Ramiz Raja on 2019-08-16.
 */
class PersonWindowInfoAdapter(private val context: Context,
                              private val persons: List<Person>) : GoogleMap.InfoWindowAdapter {
    private val windowLayout = WindowInfoView(context).apply {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setBackgroundResource(R.color.circle_image_view_border_color)
    }
    private val contentsLayout = WindowInfoView(context).apply {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setBackgroundResource(R.color.circle_image_view_border_color)
    }

    override fun getInfoContents(marker: Marker): View? {
        windowLayout.setPerson(findPerson(marker.title))
        return windowLayout
    }

    override fun getInfoWindow(marker: Marker): View {
        contentsLayout.setPerson(findPerson(marker.title))
        return contentsLayout
    }

    private fun findPerson(name: String): Person {
        return persons.first { it.name == name }
    }
}