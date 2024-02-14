package com.example.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.character.CharacterResult
import com.example.rickandmorty.character.Location
import com.example.rickandmorty.character.Origin
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding
    private val mainViewModel : MainViewModel by activityViewModels()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding?.mainRecycler?.layoutManager = layoutManager

        lifecycleScope.launch(Dispatchers.Main) {
            Log.d("checkVisible", "launch started")
            mainViewModel.getCharacters{

//                Log.d("checkVisible", "callback started")
//                Log.d("checkVisible", mainViewModel.charactersResult.value?.info?.count.toString())
//                Log.d("checkVisible", mainViewModel.charactersResult.value?.characterResults?.size.toString())
//
//                val characterResultList = mutableListOf<CharacterResult>()
//
//                mainViewModel.charactersResult.value?.characterResults?.forEach{
//                    Log.d("checkVisible", "forEach started")
//
//                    characterResultList.add(
//                        CharacterResult(
//                            created = it.created,
//                            episode = it.episode,
//                            gender = it.gender,
//                            id = it.id,
//                            image = it.image,
//                            location = it.location,
//                            name = it.name,
//                            origin = it.origin,
//                            species = it.species,
//                            status = it.status,
//                            type = it.type,
//                            url = it.url
//                        )
//                    )
//                    Log.d("checkVisible", "item added")
//                }
//                Log.d("checkVisible", "data entered")
//                adapter = RecyclerAdapter(requireContext(), characterResultList, object : ItemClickListener {
//                    override fun onItemClick(data: Int) {
//                        navigateToDetail(data)
//                    }
//                })
//                binding?.mainRecycler?.adapter = adapter
//                Log.d("checkVisible", "recycler assigned")
            }
        }



        //Проверка на тестовых данных
//        adapter = RecyclerAdapter(requireContext(), getSampleData(), object : ItemClickListener {
//            override fun onItemClick(data: Int) {
//                navigateToDetail(data)
//            }
//        })
//        binding?.mainRecycler?.adapter = adapter

            //Реализация с обсервером
        mainViewModel.charactersResult.observe(viewLifecycleOwner, Observer {
            val characterResultList = mutableListOf<CharacterResult>()
            it.characterResults?.forEach{
                characterResultList.add(
                    CharacterResult(
                        created = it.created,
                        episode = it.episode,
                        gender = it.gender,
                        id = it.id,
                        image = it.image,
                        location = it.location,
                        name = it.name,
                        origin = it.origin,
                        species = it.species,
                        status = it.status,
                        type = it.type,
                        url = it.url
                    )
                )
            }
            Log.d("checkVisible", "data entered")
            adapter = RecyclerAdapter(requireContext(), characterResultList, object : ItemClickListener {
                override fun onItemClick(data: Int) {
                    navigateToDetail(data)
                }
            })
            binding?.mainRecycler?.adapter = adapter
            Log.d("checkVisible", "recycler assigned")

        })
    }

    private fun getSampleData(): List<CharacterResult> {
        // Генерация тестовых данных
        val dataList = mutableListOf<CharacterResult>()
        for (i in 1..10) {
            dataList.add(CharacterResult(
                "1",
                listOf(),
                "1",
                0,
                "1",
                Location("1", "1"),
                "1",
                Origin("1", "1"),
                "1",
                "1",
                "1",
                "1"
            ))
        }
        return dataList
    }

    private fun navigateToDetail(itemId: Int) {
        val action = CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(itemId)
        findNavController().navigate(action)
    }
}