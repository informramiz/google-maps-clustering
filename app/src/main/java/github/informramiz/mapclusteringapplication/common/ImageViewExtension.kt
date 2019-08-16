package github.informramiz.mapclusteringapplication.common

import android.widget.ImageView
import com.squareup.picasso.Picasso


/**
 * Created by Ramiz Raja on 2019-08-16.
 */
fun ImageView.load(url: String) {
    Picasso.get()
        .load(url)
        .fit()
        .into(this)
}