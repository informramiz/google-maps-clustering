package github.informramiz.mapclusteringapplication

import android.content.Context
import android.view.LayoutInflater
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
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
    private val markerIconGenerator = IconGenerator(context)
    private val clusterIconGenerator = IconGenerator(context)
    private val dimension = context.resources.getDimension(R.dimen.map_circle_image_view_size)

    private val markerImageView: CircleImageView
    private val clusterImageView: CircleImageView

    init {
        //initialize the marker view
        val markerView = LayoutInflater.from(context).inflate(R.layout.custom_marker_view, null)
        markerIconGenerator.setContentView(markerView)
        markerImageView = markerView.findViewById(R.id.marker_image_view)

        val clusterView = LayoutInflater.from(context).inflate(R.layout.custom_cluster_view, null)
        clusterImageView = clusterView.findViewById(R.id.cluster_image_view)
        clusterIconGenerator.setContentView(clusterView)
    }
}