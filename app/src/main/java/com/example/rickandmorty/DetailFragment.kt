package com.example.rickandmorty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.character.CharacterResult
import com.example.rickandmorty.character.Location
import com.example.rickandmorty.character.Origin
import com.example.rickandmorty.databinding.FragmentCharacterBinding
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
            binding?.itemDetailedName?.text = it.name
            binding?.itemDetailedImage?.load(it.image)
            binding?.itemDetailedId?.text = it.id.toString()
            binding?.itemDetailedGender?.text = it.gender
            binding?.itemDetailedType?.text = it.type
            binding?.itemDetailedCreated?.text = it.created
            binding?.itemDetailedName?.text = it.status
            binding?.itemDetailedName?.text = it.species
            binding?.itemDetailedName?.text = it.location.name
            binding?.itemDetailedName?.text = it.origin.name

        })
    }
}