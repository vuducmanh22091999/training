package com.example.smalldemo.constant

import android.content.UriMatcher
import android.net.Uri


const val ACTION_PLUS_NUMBER = "com.example.smalldemo"
const val NUMBER_A = "number_a"
const val NUMBER_B = "number_b"
const val CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE"
const val PROVIDER_NAME = "com.example.smalldemo"
const val URL = "content://$PROVIDER_NAME/users"
val CONTENT_URI: Uri? = Uri.parse(URL)
const val ID = "id"
const val NAME = "name"
const val uriCode = 1
var uriMatcher: UriMatcher? = null
private val values: HashMap<String, String>? = null
fun static() {
    uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    uriMatcher!!.addURI(PROVIDER_NAME, "users", uriCode)
    uriMatcher!!.addURI(PROVIDER_NAME, "users/*", uriCode)
}

const val REQUEST_TAKE_PHOTO = 100
const val REQUEST_GALLERY_PHOTO = 101

