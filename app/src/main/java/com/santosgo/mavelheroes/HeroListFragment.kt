package com.santosgo.mavelheroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.santosgo.mavelheroes.data.Datasource
import com.santosgo.mavelheroes.databinding.FragmentHeroListBinding


class HeroListFragment : Fragment() {

    private var _binding : FragmentHeroListBinding? = null
    val binding
        get() = _binding!!

    val heroes = Datasource.getHeroList()

    private lateinit var heroAdapter: HeroAdapter
    private lateinit var layoutManager : LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHeroListBinding.inflate(inflater,container,false)

        initRecView()

        //       texto inicial con la lista de los heroes
        //        binding.textView.text = getString(R.string.hero_list)
        //
        //
        //        heroes.forEach { hero ->
        //            binding.textView.text = binding.textView.text as String + "\nHéroe: ${hero.name}"
        //        }

        return binding.root
    }

    fun delHero(pos : Int){
        heroes.removeAt(pos)
        binding.rvHeroes.adapter?.notifyItemRemoved(pos)
    }

    fun cloneHero(pos : Int, hero : Hero){
        heroes.add(pos+1,hero)
        binding.rvHeroes.adapter?.notifyItemInserted(pos+1)
    }

    private fun initRecView() {
        heroAdapter = HeroAdapter(heroes, { pos -> delHero(pos)}, { pos,hero -> cloneHero(pos,hero)})
        binding.rvHeroes.adapter = heroAdapter

        //Insertar las siguientes lineas en un if que compruebe la orientacion del dispositivo y actuar en consecuencia
        layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        binding.rvHeroes.layoutManager = layoutManager

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}