package com.example.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlincountries.model.Country
import com.example.kotlincountries.util.downloadFromUrl
import com.example.kotlincountries.util.placeholderProgressBar
import com.example.kotlincountries.view.CountryFragmentArgs
import com.example.kotlincountries.viewmodel.CountryViewModel
import com.example.kotlincountrylist.R
import com.example.kotlincountrylist.databinding.FragmentCountryBinding
import com.example.kotlincountrylist.databinding.FragmentFeedBinding


class CountryFragment : Fragment() {
    private lateinit var binding: FragmentCountryBinding
    private lateinit var viewModel : CountryViewModel
    private var countryUuid = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
            println(countryUuid)
        }

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData()
    {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {country ->

            country?.let {

                binding.selectedCountry = country
            /*
                with(binding){
                    countryName.text = country.countryName
                    countryCapital.text = country.countryCapital
                    countryCurrency.text = country.countryCurrency
                    countryLanguage.text = country.countryLanguage
                    countryRegion.text = country.countryRegion
                    context?.let {
                        countryImage.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))

                    }

                }

*/
            }

        })


    }


}