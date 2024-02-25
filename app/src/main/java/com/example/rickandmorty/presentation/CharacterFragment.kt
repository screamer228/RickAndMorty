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
import com.example.rickandmorty.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val MIN_PAGE: Int = 1
private const val MAX_PAGE: Int = 42

@AndroidEntryPoint
class CharacterFragment : Fragment(), ItemClickListener {

    private lateinit var _binding: FragmentCharacterBinding
    private val binding get() = _binding
    private val mainViewModel : MainViewModel by activityViewModels()
    private val adapter: RecyclerAdapter = RecyclerAdapter(this)

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

        mainViewModel.navigateToDetail.observe(viewLifecycleOwner) { itemId ->
            if (itemId != null) {
                val action = CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(itemId)
                findNavController().navigate(action)

                // Убедитесь, что сбрасываете значение в вашей ViewModel после навигации,
                // чтобы избежать повторных переходов при повторном наблюдении изменений в LiveData
                mainViewModel.resetNavigation()
            }
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
    override fun onItemClick(data: Int) {
        mainViewModel.navigateToDetail(data)
    }
}