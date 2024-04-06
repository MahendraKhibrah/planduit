package com.pens.planduit.data.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.pens.planduit.domain.models.entity.RatingStatus

class RatingPref(context: Context) {
    private val pref : SharedPreferences
    private val editor : SharedPreferences.Editor

    init {
        pref = context.getSharedPreferences(SharedPreferenceKey.prefName, Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    fun saveRatingStatus(requestRaw: RatingStatus){
        val request = Gson().toJson(requestRaw)
        editor.putString(SharedPreferenceKey.ratingStatus, request)
        editor.apply()
    }

    fun getRatingStatus() : RatingStatus? {
        val request = pref.getString(SharedPreferenceKey.ratingStatus, null)
        return if (request != null) {
            Gson().fromJson(request, RatingStatus::class.java)
        } else {
            null
        }
    }
}