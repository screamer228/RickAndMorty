package com.example.rickandmorty.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentDetailBinding
import com.example.rickandmorty.domain.entity.CharacterResultEntity
import com.example.rickandmorty.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var _binding: FragmentDetailBinding
    private val binding get() = _binding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemCharacterId: Int = arguments?.getInt("itemId") ?: 0

        mainViewModel.getCharacterById(itemCharacterId)

        observers()
    }

    private fun observers() {
        mainViewModel.characterDetail.observe(viewLifecycleOwner, Observer {
            putContent(it)
        })
    }

    private fun putContent(characterResultEntity: CharacterResultEntity) {
        binding.itemDetailedImage.load(characterResultEntity.image)
        binding.itemDetailedName.text = getString(R.string.name, characterResultEntity.name)
        binding.itemDetailedGender.text = getString(R.string.gender, characterResultEntity.gender)
        binding.itemDetailedType.text = getString(R.string.type, characterResultEntity.type)
        binding.itemDetailedLocation.text =
            getString(R.string.location, characterResultEntity.location.name)
        binding.itemDetailedSpecies.text =
            getString(R.string.species, characterResultEntity.species)
        binding.itemDetailedOrigin.text =
            getString(R.string.origin, characterResultEntity.origin.name)
        binding.itemDetailedStatus.text =
            getString(R.string.status, characterResultEntity.status)
        binding.itemDetailedCreated.text =
            getString(R.string.created, characterResultEntity.created)
    }
}