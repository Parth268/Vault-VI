package com.session.vault.storeImage

import android.content.Context
import android.database.Cursor
import android.net.Uri
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
import android.provider.MediaStore
import android.content.Intent
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Button
import com.session.vault.utils.Constants.RESULT
import com.session.vault.utils.Files.Files

class ImageAlbumFragment : Fragment() {

    private lateinit var recycleViewImage: RecyclerView
    private lateinit var imageAlbumListAdapter: ImageAlbumListAdapter
    private lateinit var imageAlbumObject: ArrayList<ImageAlbum>
    private lateinit var button: FloatingActionButton
    private lateinit var buttonDecrypt: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_image, container, false)
        recycleViewImage = view.findViewById(R.id.recycle_view_for_images)
        button = view.findViewById(R.id.add_album_image_button)
        buttonDecrypt = view.findViewById(R.id.decrypt)
        imageAlbumObject = ArrayList()
        imageAlbumObject.add(ImageAlbum("Album 1", 34))
        imageAlbumObject.add(ImageAlbum("Album 1", 34))
        imageAlbumObject.add(ImageAlbum("Album 1", 34))
        imageAlbumObject.add(ImageAlbum("Album 1", 34))
        imageAlbumListAdapter = ImageAlbumListAdapter(imageAlbumObject, requireContext())

        button.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, RESULT)
        }

        val adapter = imageAlbumListAdapter
        recycleViewImage.setHasFixedSize(true)
        recycleViewImage.layoutManager = GridLayoutManager(requireContext(), 3)
        recycleViewImage.adapter = adapter

        buttonDecrypt.setOnClickListener {
            Files.decryptImage()
        }

        return view
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val selectedImageUri = data!!.data
        val picturePath = getPath(requireActivity().applicationContext, selectedImageUri)
        Files.encryptImage(picturePath)
    }


    private fun getPath(context: Context, uri: Uri?): String {
        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor =
            uri?.let { context.contentResolver.query(it, proj, null, null, null) }!!
        if (cursor.moveToFirst()) {
            val columnIndex: Int = cursor.getColumnIndexOrThrow(proj[0])
            result = cursor.getString(columnIndex)
        }
        cursor.close()
        if (result == null) {
            result = "Not found"
        }
        return result
    }

}