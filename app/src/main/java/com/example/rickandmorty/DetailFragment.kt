package com.example.rickandmorty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.character.CharacterResult
import com.example.rickandmorty.character.Location
import com.example.rickandmorty.character.Origin
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

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
//            ?: CharacterResult("1",
//                listOf(),
//                "1",
//                0,
//                "1",
//                Location("1", "1"),
//                "1",
//                Origin("1", "1"),
//                "1",
//                "1",
//                "1",
//                "1"
//        )

//        val itemCharacter: CharacterResult? = arguments?.getParcelable("itemCharacter")
        binding?.itemDetailedId?.text = itemCharacterId.toString()

//        view.findViewById<TextView>(R.id.item_detailed_id).text = itemId?.toString()
    }
}