package cl.cat2814.sprintmodulo5.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cl.cat2814.sprintmodulo5.R
import cl.cat2814.sprintmodulo5.Shoes
import cl.cat2814.sprintmodulo5.adapters.CartShoesAdapter
import cl.cat2814.sprintmodulo5.databinding.FragmentThirdBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private lateinit var mSharedPref: SharedPreferences
    private lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdBinding.inflate(layoutInflater, container, false)

        mSharedPref = requireActivity().getSharedPreferences("ShoesPreferences", Context.MODE_PRIVATE)

        gson = Gson()

        initCartShoesAdapter()

        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.btDeleteCart.setOnClickListener {
            mSharedPref.edit().clear().apply()
            binding.rvCartShoesList.visibility = View.GONE
            binding.btDeleteCart.visibility = View.GONE

            Toast.makeText(requireContext(), "Productos eliminados exitosamente."
                , Toast.LENGTH_SHORT).show()
        }

        binding.btMainFromCart.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_thirdFragment_to_firstFragment)
        }
    }

    private fun initCartShoesAdapter() {
        val cartShoesAdapter = CartShoesAdapter()
        val shoesList: MutableList<Shoes> = getList()
        cartShoesAdapter.setData(shoesList)

        binding.rvCartShoesList.adapter = cartShoesAdapter
        binding.rvCartShoesList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getList(): MutableList<Shoes> {
        val jsonString = mSharedPref.getString("Shoes list", null)
        val listType = object : TypeToken<MutableList<Shoes>>() {}.type
        return gson.fromJson(jsonString, listType) ?: mutableListOf()
    }
}
