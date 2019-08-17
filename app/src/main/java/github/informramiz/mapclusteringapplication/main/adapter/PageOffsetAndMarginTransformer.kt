package github.informramiz.mapclusteringapplication.main.adapter

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


/**
 * Created by Ramiz Raja on 2019-08-17.
 */
class PageOffsetAndMarginTransformer(
    private val offset: Float,
    private val margin: Float
) : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        /**
         * The position parameter indicates where a given page is located relative to the center of
         * the screen. It is a dynamic property that changes as the user scrolls through the pages.
         * When a page fills the screen, its position value is 0. When a page is drawn just off the
         * right side of the screen, its position value is 1. If the user scrolls halfway between pages
         * one and two, page one has a position of -0.5 and page two has a position of 0.5.
         * https://developer.android.com/training/animation/screen-slide#pagetransformer
         */


        /**
         * there is (2 * (offset + margin)) distance between two adjacent pages as each page
         * has (offset + margin) as start and also as end margin. We are interested in only
         * keep (margin) between two adjacent pages we translate it by ((2 * offset) + margin)
         * as (2 * (offset + margin)) - ((2 * offset) + margin) = margin
         */
        val translation = position * -(2 * offset + margin)
        val viewPager = requireViewPager(page)
        if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) { //horizontal
            page.translationX = if (viewPager.layoutDirection == ViewCompat.LAYOUT_DIRECTION_RTL) {
                //rtl so reverse the direction of translation
                -translation
            } else { //LTR
                translation
            }
        } else {
            //vertical direction so translate Y instead of X
            page.translationY = translation
        }

    }

    private fun requireViewPager(page: View): ViewPager2 {
        val parent = page.parent
        val parentParent = parent.parent

        if (parent is RecyclerView && parentParent is ViewPager2) {
            return parentParent
        }

        throw IllegalStateException(
            "Expected the page view to be managed by a ViewPager2 instance."
        )
    }
}