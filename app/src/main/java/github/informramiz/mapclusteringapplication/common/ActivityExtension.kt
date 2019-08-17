package github.informramiz.mapclusteringapplication.common

import android.app.Activity
import androidx.annotation.DimenRes


/**
 * Created by Ramiz Raja on 2019-08-17.
 */
fun Activity.getDimension(@DimenRes dimenRes: Int): Float {
    return resources.getDimension(dimenRes)
}