package com.yunuscagliyan.veriparkapp.domain.preference

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(
    private var context: Context,
) {

    private lateinit var mSharedPreferences:SharedPreferences

    companion object{
        const val SHARED_PREFERENCE_FILE_NAME = "user_preference"

        const val AUTHORIZATION="authorization"
    }

    init {
        mSharedPreferences=context.getSharedPreferences(
            SHARED_PREFERENCE_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    var authorization:String?
      get() {
          return mSharedPreferences.getString(AUTHORIZATION,"")
      }
      set(value:String?) {
          val editor=mSharedPreferences.edit()
          editor.putString(AUTHORIZATION,value)
          editor.apply()
      }
}