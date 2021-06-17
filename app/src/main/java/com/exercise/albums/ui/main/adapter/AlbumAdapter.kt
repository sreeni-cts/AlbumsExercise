package com.exercise.albums.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.albums.R
import com.exercise.albums.data.model.Album
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter(
    private val albums: ArrayList<Album>
) : RecyclerView.Adapter<AlbumAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album) {
            itemView.textViewAlbum.text = album.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_album, parent,
                false
            )
        )



    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(albums[position])

    fun addData(list: List<Album>) {
        albums.addAll(list)
    }

}