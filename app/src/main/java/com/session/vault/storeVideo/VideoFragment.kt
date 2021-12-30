package com.session.vault.storeVideo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.session.vault.adapters.VideoAlbumListAdapter
import com.session.vault.modals.VideoAlbum
import com.session.vault.R

class VideoFragment : Fragment() {

    private lateinit var recycleViewVideo: RecyclerView
    private lateinit var videoAlbumListAdapter: VideoAlbumListAdapter
    private lateinit var videoAlbumObject:ArrayList<VideoAlbum>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_video, container, false)
        recycleViewVideo=view.findViewById(R.id.recycle_view_for_video)
        videoAlbumObject= ArrayList()
        videoAlbumObject.add(VideoAlbum("Album 1",34))
        videoAlbumObject.add(VideoAlbum("Album 1",34))
        videoAlbumObject.add(VideoAlbum("Album 1",34))
        videoAlbumObject.add(VideoAlbum("Album 1",34))
        videoAlbumListAdapter = VideoAlbumListAdapter(videoAlbumObject,requireContext())
        val adapter=videoAlbumListAdapter
        recycleViewVideo.setHasFixedSize(true)
        recycleViewVideo.layoutManager = GridLayoutManager(requireContext(), 3)
        recycleViewVideo.adapter = adapter
        return view
    }

}