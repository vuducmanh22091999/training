package com.example.smalldemo

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.smalldemo.broadcast.MyBroadcast
import com.example.smalldemo.constant.REQUEST_GALLERY_PHOTO
import com.example.smalldemo.constant.REQUEST_TAKE_PHOTO
import com.example.smalldemo.content_provider.FileCompressor
import com.example.smalldemo.ui_layout.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    var mPhotoFile : File? = null
    var mCompressor : FileCompressor?=null
    var isCheckCamera = false

    lateinit var myBroadcast: MyBroadcast

    private var PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniListener()
//        initBroadcast()

        mCompressor = FileCompressor(this)
    }

    private fun iniListener() {
//        actMain_tvSendMessage.setOnClickListener {
//            composeMmsMessage(actMain_etText.text.toString())
////            dialPhoneNumber("0909090909")
//        }
//
//        actMain_tvStarService.setOnClickListener {
//            checkPermission()
//
//        }
//
//        actMain_tvStopService.setOnClickListener {
//            stopService(Intent(baseContext, MyService::class.java))
//        }
//
//        actMain_tvAdd.setOnClickListener {
//            sendDataToBroadcast()
//        }

        imageViewProfilePic.setOnClickListener {
            isCheckCamera = !isCheckCamera
            requestStoragePermission(isCheckCamera)
        }

        tvConstraintLayout.setOnClickListener {
            val intentConstraintLayout = Intent(this, ConstraintLayoutActivity::class.java)
            startActivity(intentConstraintLayout)
        }

        tvLinearLayout.setOnClickListener {
            val intentLinearLayout = Intent(this, LinearLayoutActivity::class.java)
            startActivity(intentLinearLayout)
        }

        tvFrameLayout.setOnClickListener {
            val intentFrameLayout = Intent(this, FrameLayoutActivity::class.java)
            startActivity(intentFrameLayout)
        }

        tvRelativeLayout.setOnClickListener {
            val intentRelativeLayout = Intent(this, RelativeLayoutActivity::class.java)
            startActivity(intentRelativeLayout)
        }

        tvTableLayout.setOnClickListener {
            val intentTableLayout = Intent(this, TableLayoutActivity::class.java)
            startActivity(intentTableLayout)
        }

        tvGridLayout.setOnClickListener {
            val intentGridLayout = Intent(this, GridLayoutActivity::class.java)
            startActivity(intentGridLayout)
        }
    }

//    private fun checkPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (!hasPermissions(baseContext, PERMISSIONS)) {
//                requestPermissions(
//                    PERMISSIONS, 123
//                )
//                return
//            }
//        } else {
//            TODO("VERSION.SDK_INT < M")
//        }
//        startService(Intent(baseContext, MyService::class.java))
//
//    }

//    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean =
//        permissions.all {
//            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
//        }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode == 123) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                startService(Intent(baseContext, MyService::class.java))
//            } else {
//                Toast.makeText(baseContext, "Permission Denied", Toast.LENGTH_SHORT).show()
//            }
//        } else
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }
//
//    private fun composeMmsMessage(message: String?) {
//        val intent = Intent(Intent.ACTION_SEND)
//        intent.type = "text/plain"
//        intent.putExtra("sms_body", message)
//        if (intent.resolveActivity(packageManager) != null) {
//            startActivity(intent)
//        }
//    }

//    fun dialPhoneNumber(phoneNumber: String) {
//        val intent = Intent(Intent.ACTION_DIAL)
//        intent.data = Uri.parse("tel:$phoneNumber")
//        if (intent.resolveActivity(packageManager) != null) {
//            startActivity(intent)
//        }
//    }

//    private fun initBroadcast() {
//        myBroadcast = MyBroadcast()
//        val intentFilter = IntentFilter()
//        intentFilter.addAction(ACTION_PLUS_NUMBER)
//        registerReceiver(myBroadcast, intentFilter)
//    }

//    private fun sendDataToBroadcast() {
//        val a = actMain_etNumberA.text.toString().toInt()
//        val b = actMain_etNumberB.text.toString().toInt()
//        val intent = Intent().apply {
//            action = ACTION_PLUS_NUMBER
//            putExtra(NUMBER_A, a)
//            putExtra(NUMBER_B, b)
//        }
//        sendBroadcast(intent)
//    }

//    override fun onDestroy() {
//        unregisterReceiver(myBroadcast)
//        super.onDestroy()
//    }

    private fun requestStoragePermission(isCamera: Boolean) {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            .withListener(object : MultiplePermissionsListener {
               override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        if (isCamera) {
                            dispatchTakePictureIntent()
                        } else {
                            dispatchGalleryIntent()
                        }
                    }
                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied()) {
                        // show alert dialog navigating to Settings
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    TODO("Not yet implemented")
                }
            })
            .withErrorListener { error ->
                Toast.makeText(applicationContext, "Error occurred! ", Toast.LENGTH_SHORT)
                    .show()
            }
            .onSameThread()
            .check()
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private fun showSettingsDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
        builder.setMessage(
            "This app needs permission to use this feature. You can grant them in app settings."
        )
        builder.setPositiveButton("GOTO SETTINGS") { dialog, which ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
        builder.show()
    }

    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    @Throws(IOException::class)
    private fun createImageFile(): File? {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val mFileName = "JPEG_" + System.currentTimeMillis() + "_"
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(mFileName, ".jpg", storageDir)
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Create the File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                ex.printStackTrace()
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    photoFile
                )
                mPhotoFile = photoFile
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
            }
        }
    }

    private fun dispatchGalleryIntent() {
        val pickPhoto = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(pickPhoto, REQUEST_GALLERY_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                try {
                    mPhotoFile = mCompressor?.compressToFile(mPhotoFile)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                Glide.with(this@MainActivity)
                    .load(mPhotoFile)
                    .apply(
                        RequestOptions().centerCrop()
                            .circleCrop()
                            .placeholder(R.drawable.ic_launcher_background)
                    )
                    .into(imageViewProfilePic)
            } else if (requestCode == REQUEST_GALLERY_PHOTO) {
                val selectedImage = data!!.data
                try {
                    mPhotoFile =
                        mCompressor?.compressToFile(File(getRealPathFromUri(selectedImage)))
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                Glide.with(this@MainActivity)
                    .load(selectedImage?.path)
                    .apply(
                        RequestOptions().centerCrop()
                            .circleCrop()
                            .placeholder(R.drawable.ic_launcher_background)
                    )
                    .into(imageViewProfilePic)
            }
        }
    }

    fun getRealPathFromUri(contentUri: Uri?): String? {
        var cursor: Cursor? = null
        return try {
            val proj =
                arrayOf(MediaStore.Images.Media.DATA)
            cursor = contentResolver.query(contentUri!!, proj, null, null, null)
            assert(cursor != null)
            val column_index: Int? = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor?.moveToFirst()
            column_index?.let { cursor?.getString(it) }
        } finally {
            cursor?.close()
        }
    }

    ///data/user/0/com.example.smalldemo/cache/images/JPEG_20200720205507_4419125400939245771.jpg
    ///data/user/0/com.example.smalldemo/cache/images/JPEG_20200720205507_4419125400939245771.jpg
    ///data/user/0/com.example.smalldemo/cache/images/JPEG_1595253551504_4144155440894192712.jpg
    ///data/user/0/com.example.smalldemo/cache/images/JPEG_1595253551504_4144155440894192712.jpg
}

