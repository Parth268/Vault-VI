package com.session.vault.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.session.vault.R
import android.widget.TextView
import com.session.vault.modals.VideoAlbum


class VideoAlbumListAdapter(private var videoObject:ArrayList<VideoAlbum>, private var context:Context) : RecyclerView.Adapter<VideoAlbumListAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var albumName:TextView=itemView.findViewById(R.id.video_album_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater:LayoutInflater = LayoutInflater.from(context)
        return Holder(inflater.inflate(R.layout.inflate_video_album_list,parent,false))
    }

    override fun onBindViewHolder(holder:Holder, position: Int) {
        val albumName: TextView = holder.albumName
        albumName.text=videoObject[position].name
    }

    override fun getItemCount(): Int {
        return videoObject.size
    }
}