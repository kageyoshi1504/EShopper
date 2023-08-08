package com.example.eshopper.common

import android.content.Context
import android.content.res.Resources
import com.bumptech.glide.load.engine.Resource

fun Context.getWidth() = Resources.getSystem().displayMetrics.widthPixels

fun Context.getHeight() = Resources.getSystem().displayMetrics.heightPixels