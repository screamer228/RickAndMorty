package com.example.rickandmorty.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.ItemClickListener
import com.example.rickandmorty.MainViewModel
import com.example.rickandmorty.RecyclerAdapter
import com.example.rickandmorty.data.network.models.CharacterResult
import com.example.rickandmorty.data.network.models.Location
import com.example.rickandmorty.data.network.models.Origin
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding
    private val mainViewModel : MainViewModel by activityViewModels()
    private lateinit var adapter: RecyclerAdapter
    private var currentPage: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spanCount = 3
        val layoutManager = GridLayoutManager(requireContext(), spanCount)
        binding?.mainRecycler?.layoutManager = layoutManager

        binding?.buttonNext?.setOnClickListener {
            loadNextPage(currentPage)
        }
        binding?.buttonPrevious?.setOnClickListener {
            loadPrevPage(currentPage)
        }

        lifecycleScope.launch(Dispatchers.Main) {
            mainViewModel.getCharactersByPage(currentPage)
            Log.d("pagination","$currentPage")
        }

        mainViewModel.charactersResult.observe(viewLifecycleOwner, Observer {
            val characterResultList = mutableListOf<CharacterResult>()
            it.characterResults?.forEach{
                characterResultList.add(
                    CharacterResult(
                        created = it.created,
                        episode = it.episode,
                        gender = it.gender,
                        id = it.id,
                        image = it.image,
                        location = it.location,
                        name = it.name,
                        origin = it.origin,
                        species = it.species,
                        status = it.status,
                        type = it.type,
                        url = it.url
                    )
                )
            }
            adapter = RecyclerAdapter(requireContext(), characterResultList, object : ItemClickListener {
                override fun onItemClick(data: Int) {
                    navigateToDetail(data)
                }
            })
            binding?.mainRecycler?.adapter = adapter
        })
    }

    private fun loadNextPage(currentPage: Int) {
        if (currentPage < 42) {
            this.currentPage += 1
            lifecycleScope.launch(Dispatchers.Main) {
                mainViewModel.getCharactersByPage(currentPage + 1)
            }
        }
        else {
            Toast.makeText(context, "This is the last page!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadPrevPage(currentPage: Int) {
        if (currentPage > 1) {
            this.currentPage -= 1
            lifecycleScope.launch(Dispatchers.Main) {
                mainViewModel.getCharactersByPage(currentPage - 1)
            }
        }
        else {
            Toast.makeText(context, "This is the first page!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToDetail(itemId: Int) {
        val action = CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(itemId)
        findNavController().navigate(action)
    }
}