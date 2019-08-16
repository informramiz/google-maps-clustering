package github.informramiz.mapclusteringapplication.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import github.informramiz.mapclusteringapplication.R
import github.informramiz.mapclusteringapplication.common.load
import github.informramiz.mapclusteringapplication.models.Person
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.map_info_window_view.view.*


/**
 * Created by Ramiz Raja on 2019-08-16.
 */
class WindowInfoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr), LayoutContainer {
    override val containerView: View?
        get() = this

    init {
        LayoutInflater.from(context).inflate(R.layout.map_info_window_view, this, true)
    }

    fun setPerson(person: Person) {
        circle_image_view.load(person.imageUrl)
        text_view_name.text = person.name
    }
}