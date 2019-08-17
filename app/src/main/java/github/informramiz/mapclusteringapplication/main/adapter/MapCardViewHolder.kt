package github.informramiz.mapclusteringapplication.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.informramiz.mapclusteringapplication.R
import github.informramiz.mapclusteringapplication.common.load
import github.informramiz.mapclusteringapplication.models.Person


/**
 * Created by Ramiz Raja on 2019-08-17.
 */
class MapCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val profileImageView = itemView.findViewById<ImageView>(R.id.circle_image_view)
    private val nameTextView = itemView.findViewById<TextView>(R.id.text_view_name)

    fun bind(person: Person) {
        nameTextView.text = person.name
        profileImageView.load(person.imageUrl)
    }

    companion object {
        fun create(parent: ViewGroup): MapCardViewHolder {
            return LayoutInflater.from(parent.context)
                .inflate(R.layout.map_card_view, parent, false)
                .let { MapCardViewHolder(it) }
        }
    }
}