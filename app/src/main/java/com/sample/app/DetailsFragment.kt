package com.sample.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sample.R
import com.sample.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    private val viewModel: SharedViewModel by viewModels(
        ownerProducer = {requireActivity()}
    )
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_details, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.lifecycleOwner = this
        Picasso.get().load("https://duckduckgo.com/Ervin_Burrell/i/3beb0272.jpg").placeholder(R.drawable.donut_placeholder).centerCrop().fit().into(binding.characterDetailsImage)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
        binding.viewModel=viewModel
    }

    companion object {
        fun newInstance() = DetailsFragment()
    }
}