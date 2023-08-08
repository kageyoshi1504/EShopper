package com.example.eshopper

import android.content.Context
import android.graphics.Typeface
import com.akexorcist.localizationactivity.ui.LocalizationApplication
import java.util.Locale

class MyApp : LocalizationApplication() {


    override fun getDefaultLanguage(context: Context): Locale {
        return Locale.getDefault()
    }

}