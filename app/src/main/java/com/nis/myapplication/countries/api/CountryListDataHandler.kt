package com.nis.myapplication.countries.api

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.nis.myapplication.ResponseData
import com.nis.myapplication.countries.data.Countries
import java.lang.Exception

object CountryListDataHandler {

    fun getCountries(context: Context): ResponseData {
        val jsonStr = loadJsonFromAsset(context)
        if(jsonStr != null){
            val countries = Gson().fromJson<Countries>(jsonStr, Countries::class.java)
            return ResponseData.Data(countries)
        }else{
            return ResponseData.Error
        }
    }

    fun loadJsonFromAsset(context: Context): String?{
        var result:String? = null
        try {
            val inputStream = context.assets.open("countires.json")
            inputStream.apply {
                val bytes = ByteArray(available())
                read(bytes)
                close()
                result = String(bytes)
            }
        }catch (ex: Exception){
            Log.d("Utils","exception load from asset: "+ex.message)
        }
        return result
    }
}