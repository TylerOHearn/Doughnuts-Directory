package com.sample.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sample.R
import com.sample.data.entities.Characters
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
        return binding.root

    }

    fun characterImageLoad(character: Characters) {
            Picasso.get()
            .load("https://duckduckgo.com/" + character.icon.uRL)
            .placeholder(R.drawable.donut_placeholder).centerInside().fit()
            .into(binding.characterDetailsImage)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
        binding.viewModel=viewModel

        viewModel.characterSelected.observe(viewLifecycleOwner) {
            characterImageLoad(it)
        }
    }

    companion object {
        fun newInstance() = DetailsFragment()
    }
}