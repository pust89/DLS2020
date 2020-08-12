package com.pustovit.dls2020.data.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

import java.util.*

class AppPreferences(context: Context) {

    private val prefKey: String = "MyPref"
    private val prefLanguage: String = "Language"

    private val preferences: SharedPreferences = context.getSharedPreferences(prefKey, MODE_PRIVATE)


    private val currentSystemLanguage = Locale.getDefault().language

     fun checkLanguage(): Boolean {

        var savedLanguage: String? = preferences.getString(prefLanguage, null)
        if (savedLanguage == null) {
            savedLanguage = currentSystemLanguage
            preferences.edit().putString(prefLanguage, savedLanguage).apply()
            return true
        } else if (savedLanguage.equals(currentSystemLanguage)) {
            return true
        } else{
            preferences.edit().putString(prefLanguage, currentSystemLanguage).apply()
            return false
        }
    }


}