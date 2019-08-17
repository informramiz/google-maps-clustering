package github.informramiz.mapclusteringapplication.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.informramiz.mapclusteringapplication.models.Person


/**
 * Created by Ramiz Raja on 2019-08-17.
 */
class MapCardsAdapter(private val persons: List<Person>): RecyclerView.Adapter<MapCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapCardViewHolder {
        return MapCardViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    override fun onBindViewHolder(holder: MapCardViewHolder, position: Int) {
        holder.bind(persons[position])
    }
}