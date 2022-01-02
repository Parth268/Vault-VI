package com.session.vault.utils.Files

import android.util.Log
import com.session.vault.utils.Constants
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import kotlin.experimental.xor

object Files {

    fun decryptImage() {

        val fis = FileInputStream(Constants.SAVE_DIRECTROY + "abc.jpg")
        val data = ByteArray(fis.available())
        fis.read(data)

        for ((i, b) in data.withIndex()) {
            data[i] = (b xor Constants.KEY.toByte())
        }

        val dir = File(Constants.SAVE_DIRECTROY)
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("ALERT", "could not create the directories")
            }
        }

        val myFile = File(dir, "abc.jpg")
        val fos = FileOutputStream(myFile)
        fos.write(data)
        fos.close()
        fis.close()
    }

    fun encryptImage(originalFilePath: String?) {
        val fis = FileInputStream(
            originalFilePath
        )
        val data = ByteArray(fis.available())
        fis.read(data)
        for ((i, value) in data.withIndex()) {
            data[i] = (value xor Constants.KEY.toByte())
        }

        val dir = File(Constants.SAVE_DIRECTROY)

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("ALERT", "could not create the directories")
            }
        }
        val fos = FileOutputStream(File(dir, "abc.jpg"))
        fos.write(data)
        fos.close()
        fis.close()

    }

}