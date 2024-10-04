package com.example.kotlincountries.adapter

import android.view.View
import com.example.kotlincountries.model.Country
import com.example.kotlincountrylist.databinding.ItemCountryBinding

interface CountryClickListener {

    fun onCountryClicked(country: Country, view: View)



}