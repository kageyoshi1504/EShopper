package com.example.eshopper.common

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.lang.Exception
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

object Common {
    fun toast(context : Context, info : String)
    {
        Toast.makeText(
            context,
            info,
            Toast.LENGTH_SHORT
        )
            .show()
    }

    fun formatCurrency(value : Double) : String{
        val locale = Locale("vi","VN")
        val format = NumberFormat.getCurrencyInstance(locale)
        return format.format(value)

    }


    fun getID() : Long{
        return System.currentTimeMillis()
    }


    fun getCurrentTime() : String {

        val timeFormat = SimpleDateFormat("HH:mm:ss")
        return timeFormat.format(Calendar.getInstance().time)

    }

    fun getCurrentDate() : String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return dateFormat.format(Calendar.getInstance().time)
    }

    fun getId() : String{
        return UUID.randomUUID().toString()
    }
}