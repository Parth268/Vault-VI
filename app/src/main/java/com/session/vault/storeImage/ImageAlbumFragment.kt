package com.session.vault.storeImage

import android.content.Context
import android.database.Cursor
import android.database.Observable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.session.vault.R
import com.session.vault.adapters.ImageAlbumListAdapter
import com.session.vault.modals.ImageAlbum
import com.session.vault.utils.Constants
import com.session.vault.utils.Constants.DIRECTORY
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URI.*
import java.nio.charset.StandardCharsets
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.CipherOutputStream
import android.provider.MediaStore
import android.content.Intent
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ImageAlbumFragment : Fragment() {

    private lateinit var recycleViewImage:RecyclerView
    private lateinit var imageAlbumListAdapter: ImageAlbumListAdapter
    private lateinit var imageAlbumObject:ArrayList<ImageAlbum>
    private lateinit var button:FloatingActionButton
    private var RESULT_OK=1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View= inflater.inflate(R.layout.fragment_image, container, false)
        recycleViewImage=view.findViewById(R.id.recycle_view_for_images)
        button=view.findViewById(R.id.add_album_image_button)
        imageAlbumObject= ArrayList()
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumObject.add(ImageAlbum("Album 1",34))
        imageAlbumListAdapter = ImageAlbumListAdapter(imageAlbumObject,requireContext())

        button.setOnClickListener {
          //  startActivityForResult(Intent(requireContext(),Intent.ACTION_VIEW), RESULT_OK);
        }

        val adapter=imageAlbumListAdapter
        recycleViewImage.setHasFixedSize(true)
        recycleViewImage.layoutManager = GridLayoutManager(requireContext(), 3)
        recycleViewImage.adapter = adapter
        return view
    }

    fun encryptImage(originalFilePath: String?) {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        val fileToWrite = "my_sensitive_data.txt"
        val encryptedFile = EncryptedFile.Builder(
            File(DIRECTORY, fileToWrite),
            requireContext(),
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        val fileContent = "MY SUPER-SECRET INFORMATION"
            .toByteArray(StandardCharsets.UTF_8)
        encryptedFile.openFileOutput().apply {
            write(fileContent)
            flush()
            close()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val selectedImageUri = data!!.data
            val picturePath = getPath(requireActivity().applicationContext, selectedImageUri)
            Log.d("Picture Path", picturePath.toString())
        }
    }



    fun getPath(context: Context, uri: Uri?): String? {
        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = uri?.let { context.getContentResolver().query(it, proj, null, null, null) }!!
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val column_index: Int = cursor.getColumnIndexOrThrow(proj[0])
                result = cursor.getString(column_index)
            }
            cursor.close()
        }
        if (result == null) {
            result = "Not found"
        }
        return result
    }

}