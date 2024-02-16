package com.example.rickandmorty.presentation

import android.os.Bundle
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
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val MIN_PAGE: Int = 1
private const val MAX_PAGE: Int = 42

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private lateinit var _binding: FragmentCharacterBinding
    private val binding get() = _binding
    private val mainViewModel : MainViewModel by activityViewModels()
    private lateinit var adapter: RecyclerAdapter
    private var currentPage: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
            mainViewModel.getCharactersByPage(currentPage)

        observers()

    }

    private fun observers() {
        mainViewModel.charactersResult.observe(viewLifecycleOwner) {
            adapter = RecyclerAdapter(requireContext(), it.characterResults,
                object : ItemClickListener {
                override fun onItemClick(data: Int) {
                    navigateToDetail(data)
                }
            }) {

            }
            binding.rvCharacterList.adapter = adapter
        }
    }

    private fun loadNextPage(currentPage: Int) {
        if (currentPage < MAX_PAGE) {
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
        if (currentPage > MIN_PAGE) {
            this.currentPage -= 1
            lifecycleScope.launch(Dispatchers.Main) {
                mainViewModel.getCharactersByPage(currentPage - 1)
            }
        }
        else {
            Toast.makeText(context, "This is the first page!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickListeners(){
        binding.buttonNext.setOnClickListener {
            loadNextPage(currentPage)
        }
        binding.buttonPrevious.setOnClickListener {
            loadPrevPage(currentPage)
        }
    }

    private fun navigateToDetail(itemId: Int) {
        val action = CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(itemId)
        findNavController().navigate(action)
    }
}