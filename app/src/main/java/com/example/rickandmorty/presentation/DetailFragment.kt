package com.example.rickandmorty.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.rickandmorty.databinding.FragmentDetailBinding
import com.example.rickandmorty.domain.entity.CharacterResultEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var _binding: FragmentDetailBinding
    private val binding get() = _binding
    private val mainViewModel : MainViewModel by activityViewModels()

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

    private fun observers(){
        mainViewModel.characterDetailResult.observe(viewLifecycleOwner, Observer {
            putContent(it)
        })
    }

    private fun putContent(characterResultEntity: CharacterResultEntity){
        binding.itemDetailedImage.load(characterResultEntity.image)
        binding.itemDetailedName.text = "Name: ${characterResultEntity.name}"
        binding.itemDetailedGender.text = "Gender: ${characterResultEntity.gender}"
        binding.itemDetailedType.text = "Type: ${characterResultEntity.type}"
        binding.itemDetailedLocation.text = "Location: ${characterResultEntity.location.name}"
        binding.itemDetailedSpecies.text = "Species: ${characterResultEntity.species}"
        binding.itemDetailedOrigin.text = "Origin: ${characterResultEntity.origin.name}"
        binding.itemDetailedStatus.text = "Status: ${characterResultEntity.status}"
        binding.itemDetailedCreated.text = "Created: ${characterResultEntity.created}"
    }
}