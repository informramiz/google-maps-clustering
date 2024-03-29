package github.informramiz.mapclusteringapplication.clusterrenderer

import android.content.Context
import android.view.LayoutInflater
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import github.informramiz.mapclusteringapplication.R
import github.informramiz.mapclusteringapplication.customviews.CircleImageView
import github.informramiz.mapclusteringapplication.models.Person


/**
 * Created by Ramiz Raja on 2019-08-10.
 */
class PersonClusterRenderer(
    context: Context,
    map: GoogleMap?,
    clusterManager: ClusterManager<Person>
) : DefaultClusterRenderer<Person>(context, map, clusterManager) {
    private val markerIconGenerator = IconGenerator(context).apply {
        setStyle(IconGenerator.STYLE_PURPLE)
    }

    private val markerImageView: CircleImageView
    private val picassoMarkers = mutableListOf<PicassoMarker>()

    init {
        //initialize the marker view
        val markerView = LayoutInflater.from(context).inflate(R.layout.custom_marker_view, null)
        markerIconGenerator.setContentView(markerView)
        markerIconGenerator.setBackground(null)
        markerImageView = markerView.findViewById(R.id.marker_image_view)
    }

    override fun onBeforeClusterItemRendered(item: Person, markerOptions: MarkerOptions) {
        val icon = markerIconGenerator.makeIcon()
        markerOptions
            .icon(BitmapDescriptorFactory.fromBitmap(icon))
            .title(item.name)
    }

    override fun onClusterItemRendered(clusterItem: Person, marker: Marker) {
        val picassoMarker = PicassoMarker(marker, markerImageView, markerIconGenerator)
        picassoMarker.load(clusterItem.imageUrl)
        picassoMarkers.add(picassoMarker)
    }

    override fun shouldRenderAsCluster(cluster: Cluster<Person>?): Boolean {
        return false
    }

    override fun onRemove() {
        super.onRemove()
        picassoMarkers.clear() 
    }

    fun setMarkerSelected(marker: Marker, isSelected: Boolean) {
        unselectedAllMarkers()
        setMarkerSelection(marker, isSelected)
    }

    fun setMarkerSelected(item: Person, isSelected: Boolean) {
        unselectedAllMarkers()
        val marker = getMarker(item)
        marker ?: return
        setMarkerSelected(marker, isSelected)
    }

    private fun findPicassoMarker(marker: Marker): PicassoMarker? {
        return picassoMarkers.firstOrNull { it.marker == marker }
    }

    private fun unselectedAllMarkers() {
        picassoMarkers.forEach { setMarkerSelection(it.marker, false) }
    }

    private fun setMarkerSelection(marker: Marker, isSelected: Boolean) {
        val markerItem = getClusterItem(marker)
        val picassoMarker = findPicassoMarker(marker)
        picassoMarker ?: return
        picassoMarker.imageView.isSelected = isSelected
        picassoMarker.load(markerItem.imageUrl)

        if (isSelected) {
            marker.zIndex = 1f
            marker.showInfoWindow()
        } else {
            marker.zIndex = 0f
            marker.hideInfoWindow()
        }
    }
}