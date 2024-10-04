package com.sarpertezel.kotlincountries.adapter

import android.view.View
import com.sarpertezel.kotlincountries.model.Country

interface CountryClickListener {

    fun onCountryClicked(country: Country, view: View)



}