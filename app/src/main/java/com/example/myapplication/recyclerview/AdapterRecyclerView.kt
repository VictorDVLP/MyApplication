package com.example.myapplication.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.room.EntityElements

// Adapter for the [RecyclerView] in [MainFragment]. Displays [EntityElements] data object.

class AdapterRecyclerView :
    ListAdapter<EntityElements, AdapterRecyclerView.ElementViewHolder>(ElementsComparator()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ElementViewHolder {
        return ElementViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.elements)
    }

    class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val elementItemView: TextView = itemView.findViewById(R.id.item_element)

        fun bind(text: String?) {
            elementItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ElementViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list, parent, false)
                return ElementViewHolder(view)
            }
        }
    }

    class ElementsComparator : DiffUtil.ItemCallback<EntityElements>() {
        override fun areItemsTheSame(oldItem: EntityElements, newItem: EntityElements): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: EntityElements, newItem: EntityElements): Boolean {
            return oldItem.elements == newItem.elements
        }
    }
}