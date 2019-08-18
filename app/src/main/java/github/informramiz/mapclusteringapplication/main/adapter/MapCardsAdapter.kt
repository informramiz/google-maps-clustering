package github.informramiz.mapclusteringapplication.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import github.informramiz.mapclusteringapplication.models.Person


/**
 * Created by Ramiz Raja on 2019-08-17.
 */
class MapCardsAdapter():
    ListAdapter<Person, MapCardViewHolder>(Person.DIFF_ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapCardViewHolder {
        return MapCardViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MapCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}