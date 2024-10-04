package com.sarpertezel.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sarpertezel.kotlincountries.adapter.CountryAdapter
import com.sarpertezel.kotlincountries.viewmodel.FeedViewModel
import com.sarpertezel.kotlincountrylist.databinding.FragmentFeedBinding


class FeedFragment : Fragment() {

    private lateinit var binding : FragmentFeedBinding
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()


        with(binding.countryList)
        {
            layoutManager = LinearLayoutManager(context)
            adapter = countryAdapter
        }


        observeLiveData()

      with(binding)
      {
          swipeRefreshLayout.setOnRefreshListener {
              countryList.visibility = View.GONE
              countryError.visibility = View.GONE
              countryLoading.visibility = View.VISIBLE
              viewModel.refreshFromAPI()
              swipeRefreshLayout.isRefreshing = false
          }
      }
    }

   private fun observeLiveData()
    {
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->

            countries?.let {
                binding.countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {error ->

            error?.let {
                if(it)
                {
                    binding.countryError.visibility = View.VISIBLE

                }
                else
                {
                    binding.countryError.visibility = View.GONE

                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {loading ->

            loading?.let {
                if(it)
                {
                    binding.countryLoading.visibility = View.VISIBLE
                    binding.countryList.visibility = View.GONE
                    binding.countryList.visibility = View.GONE
                }
                else
                {
                    binding.countryLoading.visibility = View.GONE
                }
            }
        })
    }

}