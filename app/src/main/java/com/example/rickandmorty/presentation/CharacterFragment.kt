package com.example.rickandmorty.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint

private const val MIN_PAGE: Int = 1
private const val MAX_PAGE: Int = 42

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private lateinit var _binding: FragmentCharacterBinding
    private val binding get() = _binding
    private val mainViewModel : MainViewModel by activityViewModels()
    private var adapter: RecyclerAdapter = RecyclerAdapter(
        object : ItemClickListener {
            override fun onItemClick(data: Int) {
                navigateToDetail(data)
            }
        }) {

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCharacterList.adapter = adapter

        clickListeners()

        mainViewModel.getCharactersByPage(mainViewModel.currentPage)

        observers()

    }

    private fun observers() {
        mainViewModel.charactersResult.observe(viewLifecycleOwner) {
            adapter.updateList(it.characterResults)

        }
    }

    private fun clickListeners(){
        binding.buttonNext.setOnClickListener {
            if (mainViewModel.currentPage < MAX_PAGE) {
                mainViewModel.currentPage ++
                mainViewModel.loadNextPage()
            }
            else {
                Toast.makeText(context, "This is the last page!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonPrevious.setOnClickListener {
            if (mainViewModel.currentPage > MIN_PAGE){
                mainViewModel.currentPage --
                mainViewModel.loadPrevPage()
            }
            else {
                Toast.makeText(context, "This is the last page!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToDetail(itemId: Int) {
        val action = CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(itemId)
        findNavController().navigate(action)
    }
}