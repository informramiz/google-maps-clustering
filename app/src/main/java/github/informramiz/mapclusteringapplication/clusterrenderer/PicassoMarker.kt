package github.informramiz.mapclusteringapplication.clusterrenderer

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.ui.IconGenerator
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import github.informramiz.mapclusteringapplication.R


/**
 * Created by Ramiz Raja on 2019-08-16.
 */
class PicassoMarker (
    private val marker: Marker,
    private val imageView: ImageView,
    private val iconGenerator: IconGenerator
): Target {
    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        imageView.setImageDrawable(placeHolderDrawable)
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        //ignore
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom) {
        imageView.setImageBitmap(bitmap)
        val icon = iconGenerator.makeIcon()
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(icon))
    }
}

fun PicassoMarker.load(url: String) {
    Picasso.get().load(url)
        .resizeDimen(R.dimen.map_circle_image_view_size, R.dimen.map_circle_image_view_size)
        .placeholder(R.drawable.placeholder_image)
        .transform(PicassoCircleTransform())
        .into(this)
}