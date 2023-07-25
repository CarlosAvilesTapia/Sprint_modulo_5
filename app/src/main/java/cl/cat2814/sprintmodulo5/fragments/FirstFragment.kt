package cl.cat2814.sprintmodulo5.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import cl.cat2814.sprintmodulo5.R
import cl.cat2814.sprintmodulo5.ShoesInventory
import cl.cat2814.sprintmodulo5.adapters.ShoesAdapter
import cl.cat2814.sprintmodulo5.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)

        initShoesAdapter()

        initListeners()

        return binding.root

    }

    private fun initListeners() {
        binding.btCart.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_firstFragment_to_thirdFragment)
        }
    }

    private fun initShoesAdapter() {
        val shoesAdapter = ShoesAdapter()
        val shoesInventory = ShoesInventory.getShoesInventory()
        shoesAdapter.setData(shoesInventory)

        binding.rvShoesList.adapter = shoesAdapter
        binding.rvShoesList.layoutManager = GridLayoutManager(context,2)
    }
}
