package com.sample.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.R
import com.sample.data.entities.Characters
import com.sample.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val viewModel: SharedViewModel by viewModels(
        ownerProducer = {requireActivity()}
    )
    private lateinit var adapter: RecyclerViewAdapter

    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        inflater.inflate(R.layout.fragment_list, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            //viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]

        val castObserver = Observer<ArrayList<Characters>> { newCast ->
            adapter = RecyclerViewAdapter(newCast)
            adapter.setOnClickListener(object :
                RecyclerViewAdapter.OnClickListener {
                    override fun onClick(character: Characters) {
                        viewModel.setSelectedCharacter(character)
                        (activity as MainActivity).openPanel()
                    }
                }
            )

            binding.cast.layoutManager = LinearLayoutManager(context)
            binding.cast.adapter = adapter
        }

        viewModel.currentCast.observe(viewLifecycleOwner, castObserver)
    }
}