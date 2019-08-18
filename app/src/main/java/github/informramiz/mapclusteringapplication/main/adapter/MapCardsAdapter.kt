package github.informramiz.mapclusteringapplication.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.informramiz.mapclusteringapplication.models.Person


/**
 * Created by Ramiz Raja on 2019-08-17.
 */
class MapCardsAdapter(private val onClickCallback: ((item: Person) -> Unit)? = null):
    ListAdapter<Person, MapCardViewHolder>(Person.DIFF_ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapCardViewHolder {
        return MapCardViewHolder.create(parent).apply {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onClickCallback?.invoke(getItem(adapterPosition))
                }
            }
        }
    }

    override fun onBindViewHolder(holder: MapCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getItemPosition(item: Person): Int? {
        for (i in 0 until itemCount) {
            if (getItem(i) == item) {
                return i
            }
        }
        return null
    }

    public override fun getItem(position: Int): Person {
        return super.getItem(position)
    }
}