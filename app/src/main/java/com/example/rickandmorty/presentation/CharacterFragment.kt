package com.example.rickandmorty.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
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
            binding.errorPlug.visibility = INVISIBLE
            adapter.updateList(it.characterResults)
            binding.rvCharacterList.visibility = VISIBLE
        }

        mainViewModel.navigateToDetail.observe(viewLifecycleOwner) {
            if (it != null) {
                val action =
                    CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(it)
                findNavController().navigate(action)
            }
            mainViewModel.resetNavigation()
        }

        mainViewModel.charactersError.observe(viewLifecycleOwner) {
            binding.rvCharacterList.visibility = INVISIBLE
            binding.errorPlug.text = getString(R.string.please_check_your_internet_next_line, it)
            binding.errorPlug.visibility = VISIBLE
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
}
