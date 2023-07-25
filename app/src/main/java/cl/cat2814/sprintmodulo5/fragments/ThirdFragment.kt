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
import cl.cat2814.sprintmodulo5.databinding.ItemShoesCartBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {

    lateinit var binding: FragmentThirdBinding
    lateinit var mSharedPref: SharedPreferences
    lateinit var gson: Gson
    lateinit var itemBinding: ItemShoesCartBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdBinding.inflate(layoutInflater, container, false)
        itemBinding = ItemShoesCartBinding.inflate(layoutInflater, container, false)

        mSharedPref = requireActivity().getSharedPreferences("ShoesPreferences", Context.MODE_PRIVATE)

        gson = Gson()

        var shoesList: MutableList<Shoes> = getList()

        val cartShoesAdapter = CartShoesAdapter(this)

        cartShoesAdapter.setData(shoesList)
        binding.rvCartShoesList.adapter = cartShoesAdapter
        binding.rvCartShoesList.layoutManager = LinearLayoutManager(requireContext())

        binding.btDeleteCart.setOnClickListener {
            mSharedPref.edit().clear().apply()
            binding.rvCartShoesList.visibility = View.GONE
            binding.btDeleteCart.visibility = View.GONE

            Toast.makeText(requireContext(), "Productos eliminados exitosamente.", Toast.LENGTH_SHORT).show()

        }



        binding.btMainFromCart.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_thirdFragment_to_firstFragment)
        }

        return binding.root
    }

    private fun getList(): MutableList<Shoes> {
        val jsonString = mSharedPref.getString("Shoes list", null)
        val listType = object : TypeToken<MutableList<Shoes>>() {}.type
        return gson.fromJson(jsonString, listType) ?: mutableListOf()

    }

   /* private fun initCartShoesAdapter(shoesList: List<Shoes>) {
        val cartShoesAdapter = CartShoesAdapter(shoesList)

        binding.rvCartShoesList.adapter = cartShoesAdapter
        binding.rvCartShoesList.layoutManager = LinearLayoutManager(requireContext())
    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}