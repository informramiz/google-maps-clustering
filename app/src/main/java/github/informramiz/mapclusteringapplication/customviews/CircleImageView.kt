package github.informramiz.mapclusteringapplication.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.ViewOutlineProvider
import androidx.appcompat.widget.AppCompatImageView


/**
 * Created by Ramiz Raja on 2019-08-16.
 */
class CircleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    init {
        outlineProvider = ViewOutlineProvider.BACKGROUND
        clipToOutline = true
    }
}