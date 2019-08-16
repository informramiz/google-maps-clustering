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
        picassoMarker.load("https://cdn.pixabay.com/photo/2014/11/13/06/12/boy-529067_1280.jpg")
        picassoMarkers.add(picassoMarker)
    }

    override fun shouldRenderAsCluster(cluster: Cluster<Person>?): Boolean {
        return false
    }

    override fun onRemove() {
        super.onRemove()
        picassoMarkers.clear() 
    }
}