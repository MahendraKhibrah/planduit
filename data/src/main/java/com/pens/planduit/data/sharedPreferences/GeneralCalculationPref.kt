package com.pens.planduit.data.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.pens.planduit.domain.models.request.InvestmentRequest
import com.pens.planduit.domain.models.request.RiskProfileRequest

class GeneralCalculationPref(context: Context) {

    private val pref : SharedPreferences
    private val editor : SharedPreferences.Editor

    init {
        pref = context.getSharedPreferences(SharedPreferenceKey.prefName, Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    fun saveInvestmentRequest(requestRaw: InvestmentRequest){
        val request = Gson().toJson(requestRaw)
        editor.putString(SharedPreferenceKey.investmentRequest, request)
        editor.apply()
    }

    fun getInvestmentRequest() : InvestmentRequest? {
        val request = pref.getString(SharedPreferenceKey.investmentRequest, null)
        return if (request != null) {
            Gson().fromJson(request, InvestmentRequest::class.java)
        } else {
            null
        }
    }

    fun getRiskProfileRequest() : RiskProfileRequest? {
        val request = pref.getString(SharedPreferenceKey.riskProfileRequest, null)
        return if (request != null) {
            Gson().fromJson(request, RiskProfileRequest::class.java)
        } else {
            null
        }
    }

    fun saveRiskProfileRequest(requestRaw: RiskProfileRequest){
        val request = Gson().toJson(requestRaw)
        editor.putString(SharedPreferenceKey.riskProfileRequest, request)
        editor.apply()
    }
}