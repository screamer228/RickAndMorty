package com.example.rickandmorty.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.app.App
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.presentation.viewmodels.MainViewModel
import com.example.rickandmorty.presentation.viewmodels.MainViewModelFactory
import javax.inject.Inject

class CharacterFragment : Fragment(), ItemClickListener {

    private lateinit var _binding: FragmentCharacterBinding
    private val binding get() = _binding
    private val adapter: RecyclerAdapter = RecyclerAdapter(this)

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity().applicationContext as App).appComponent.injectCharacter(this)

        mainViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        binding.rvCharacterList.adapter = adapter

        clickListeners()

        mainViewModel.getCharactersByPage(mainViewModel.currentPage)

        observers()

    }

    private fun observers() {
        mainViewModel.characters.observe(viewLifecycleOwner) {
            adapter.updateList(it.characterResults)
        }

        mainViewModel.navigateToDetail.observe(viewLifecycleOwner) { itemId ->
            if (itemId != null) {
                val action =
                    CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(itemId)
                findNavController().navigate(action)

                mainViewModel.resetNavigation()
            }
        }
    }

    private fun clickListeners() {
        binding.buttonNext.setOnClickListener {
            mainViewModel.loadNextPage()
        }

        binding.buttonPrevious.setOnClickListener {
            mainViewModel.loadPrevPage()
        }
    }

    override fun onItemClick(data: Int) {
        mainViewModel.navigateToDetail(data)
    }

    fun toastFirstPage() {
        Toast.makeText(
            context,
            getString(R.string.this_is_the_first_page),
            Toast.LENGTH_SHORT
        )
            .show()
    }

    fun toastLastPage() {
        Toast.makeText(
            context,
            getString(R.string.this_is_the_last_page),
            Toast.LENGTH_SHORT
        )
            .show()
    }
}
