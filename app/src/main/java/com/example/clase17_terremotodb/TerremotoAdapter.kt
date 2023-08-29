package com.example.clase17_terremotodb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class TerremotoAdapter: androidx.recyclerview.widget.ListAdapter<Terremoto, TerremotoAdapter.ViewHolder>(DiffCallBack) {
    lateinit var onItemClickListener: (Terremoto) -> Unit

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val tvMag: TextView = view.findViewById(R.id.textViewMag)
        private val tvPlace: TextView = view.findViewById(R.id.textViewPlace)
        private val ivChevron: ImageView = view.findViewById(R.id.imageViewChevron)

        fun bind(terremoto: Terremoto) {
            tvMag.text = terremoto.magnitude
            tvPlace.text = terremoto.place
            ivChevron.setOnClickListener {
                onItemClickListener(terremoto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val terremoto = getItem(position)
        holder.bind(terremoto)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Terremoto>() {
        override fun areItemsTheSame(oldItem: Terremoto, newItem: Terremoto): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Terremoto, newItem: Terremoto): Boolean {
            return oldItem == newItem
        }
    }
}
