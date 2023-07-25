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

    lateinit var binding: FragmentFirstBinding

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

        // Inflate the layout for this fragment
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
/*
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}