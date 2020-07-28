package com.example.smalldemo.content_provider

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.example.smalldemo.R
import com.example.smalldemo.adapter.ShowImageAdapter
import com.example.smalldemo.constant.PERMISSION_CODE_READ
import com.example.smalldemo.constant.PERMISSION_CODE_WRITE
import com.example.smalldemo.data.ImageModel
import kotlinx.android.synthetic.main.activity_show_image.*

class ShowImageActivity : AppCompatActivity() {
    private lateinit var showImageAdapter: ShowImageAdapter
    private val listImage = ArrayList<ImageModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        initAdapter()
        checkPermissionForImage()
    }

    private fun initAdapter() {
        showImageAdapter = ShowImageAdapter(listImage)
//        rcvShowImage.layoutManager = LinearLayoutManager(this)
//        rcvShowImage.setHasFixedSize(true)
        rcvShowImage.adapter = showImageAdapter
    }

    private fun loadImagesFromDevice() {
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.MediaColumns.DATA),
            null,
            null,
            MediaStore.Images.Media.DEFAULT_SORT_ORDER
        )
        cursor?.run {
            while (moveToNext()) {
                val pathImage =
                    getString(getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))
                listImage.add(ImageModel(pathImage))
            }
            close()
            showImageAdapter.notifyDataSetChanged()
        }
    }

    private fun checkPermissionForImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                && (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ) {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionCoarse = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                requestPermissions(permission, PERMISSION_CODE_READ)
                requestPermissions(permissionCoarse, PERMISSION_CODE_WRITE)
            } else {
                loadImagesFromDevice()
            }
        }
    }
}
