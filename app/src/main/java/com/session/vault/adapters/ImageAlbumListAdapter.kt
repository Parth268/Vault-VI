package com.session.vault.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.session.vault.R
import android.widget.TextView
import com.session.vault.modals.ImageAlbum


class ImageAlbumListAdapter(private var imageObject:ArrayList<ImageAlbum>, private  var context:Context) : RecyclerView.Adapter<ImageAlbumListAdapter.Holder>() {

    private lateinit var clickListener:setItemClickListener

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener
    {

        private lateinit var clickListener:setItemClickListener


        fun itemBind(imageObject: ImageAlbum){
            val albumName:TextView=itemView.findViewById(R.id.video_album_name)
            albumName.text=imageObject.name
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            clickListener.onItemClickListener(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater:LayoutInflater = LayoutInflater.from(context)
        return Holder(inflater.inflate(R.layout.inflate_image_album_list,parent,false))
    }

    override fun onBindViewHolder(holder:Holder, position: Int) {
        //clickListener.onItemClickListener(position)
        holder.itemBind(imageObject[position])
    }


    override fun getItemCount(): Int {
        return imageObject.size
    }
}
 interface setItemClickListener{
    fun onItemClickListener(position: Int)
}