package com.session.vault.storeImage.imageList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.session.vault.R
import com.session.vault.adapters.ImageAlbumListAdapter
import com.session.vault.modals.ImageAlbum

class ImageListFragment : Fragment() {

    private lateinit var recycleViewImageList: RecyclerView
    private lateinit var imageAlbumListAdapter: ImageAlbumListAdapter
    private lateinit var imageAlbumObject:ArrayList<ImageAlbum>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View=inflater.inflate(R.layout.fragment_image_list, container, false)
        recycleViewImageList=view.findViewById(R.id.recycle_view_for_images_List)
        imageAlbumObject= ArrayList()
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumListAdapter = ImageAlbumListAdapter(imageAlbumObject,requireContext())
        val adapter=imageAlbumListAdapter
        recycleViewImageList.setHasFixedSize(true)
        recycleViewImageList.layoutManager = GridLayoutManager(requireContext(),3)
        recycleViewImageList.adapter = adapter
        return view
    }
}