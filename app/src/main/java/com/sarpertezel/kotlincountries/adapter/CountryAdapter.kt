package com.sarpertezel.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sarpertezel.kotlincountries.model.Country
import com.sarpertezel.kotlincountries.view.FeedFragmentDirections
import com.sarpertezel.kotlincountrylist.R
import com.sarpertezel.kotlincountrylist.databinding.ItemCountryBinding

class CountryAdapter(val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {



    class CountryViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val binding = ItemCountryBinding.inflate(inflater, parent, false)
        val binding = DataBindingUtil.inflate<ItemCountryBinding>(inflater, R.layout.item_country,parent,false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.binding.country = countryList[position]
        holder.binding.listener = this
    /*
        with(holder.binding)
        {
            name.text = countryList[position].countryName
            region.text = countryList[position].countryRegion
            imageView.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(root.context))

            root.setOnClickListener {
                val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)

                Navigation.findNavController(it).navigate(action)
            }
        }



      */




    }

    fun updateCountryList(newCountryList : List<Country>)
    {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()

    }

    override fun onCountryClicked(country: Country, view: View) {
        val uuid = country.uuid
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(view).navigate(action)
    }
}
