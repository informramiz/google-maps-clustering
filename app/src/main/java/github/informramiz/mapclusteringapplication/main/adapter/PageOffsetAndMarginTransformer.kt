package github.informramiz.mapclusteringapplication.main.adapter

import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2


/**
 * Created by Ramiz Raja on 2019-08-17.
 */
class PageOffsetAndMarginTransformer(private val offset: Float,
                                     private val margin: Float) : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        Log.i(javaClass.simpleName, "page position: $position")
        page.translationX =  position * -(2 * offset + margin)
    }
}