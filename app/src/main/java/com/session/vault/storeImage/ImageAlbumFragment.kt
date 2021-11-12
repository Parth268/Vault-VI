package com.session.vault.storeImage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.session.vault.R
import com.session.vault.adapters.ImageAlbumListAdapter
import com.session.vault.modals.ImageAlbum


class ImageAlbumFragment : Fragment() {

    private lateinit var recycleViewImage:RecyclerView
    private lateinit var imageAlbumListAdapter: ImageAlbumListAdapter
    private lateinit var imageAlbumObject:ArrayList<ImageAlbum>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View= inflater.inflate(R.layout.fragment_image, container, false)
        recycleViewImage=view.findViewById(R.id.recycle_view_for_images)
        imageAlbumObject= ArrayList()
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumListAdapter = ImageAlbumListAdapter(imageAlbumObject,requireContext())
        val adapter=imageAlbumListAdapter
        recycleViewImage.setHasFixedSize(true)
        recycleViewImage.layoutManager = GridLayoutManager(requireContext(), 2)
        recycleViewImage.adapter = adapter
        return view
    }
}