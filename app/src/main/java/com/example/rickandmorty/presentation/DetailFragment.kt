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
import com.example.rickandmorty.MainViewModel
import com.example.rickandmorty.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let { DetailFragmentArgs.fromBundle(it) }
        val itemCharacterId: Int = args?.itemId ?: 0

        lifecycleScope.launch(Dispatchers.Main) {
            mainViewModel.getCharacterById(itemCharacterId)

            }

        mainViewModel.characterDetailResult.observe(viewLifecycleOwner, Observer {
            binding?.itemDetailedName?.text = "Name: ${it.name}"
            binding?.itemDetailedImage?.load(it.image)
            binding?.itemDetailedGender?.text = "Gender: ${it.gender}"
            binding?.itemDetailedType?.text = "Type: ${it.type}"
            binding?.itemDetailedLocation?.text = "Location: ${it.location.name}"
            binding?.itemDetailedSpecies?.text = "Species: ${it.species}"
            binding?.itemDetailedOrigin?.text = "Origin: ${it.origin.name}"
            binding?.itemDetailedStatus?.text = "Status: ${it.status}"
            binding?.itemDetailedCreated?.text = "Created: ${it.created}"

        })
    }
}