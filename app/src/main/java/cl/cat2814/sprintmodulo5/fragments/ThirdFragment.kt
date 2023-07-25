package cl.cat2814.sprintmodulo5.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cl.cat2814.sprintmodulo5.R
import cl.cat2814.sprintmodulo5.Shoes
import cl.cat2814.sprintmodulo5.ShoesInventory
import cl.cat2814.sprintmodulo5.adapters.CartShoesAdapter
import cl.cat2814.sprintmodulo5.databinding.FragmentThirdBinding
import cl.cat2814.sprintmodulo5.databinding.ItemShoesCartBinding

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
    lateinit var itemBinding: ItemShoesCartBinding
    lateinit var mSharedPref: SharedPreferences

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

        // Lectura de las preferencias del segundo fragmento
        mSharedPref = requireContext().getSharedPreferences("ShoesPreferences", Context.MODE_PRIVATE)
        val shoeName = mSharedPref.getString("Name","").toString()
        val shoePrice = mSharedPref.getInt("Price",0)
        val imgUrl = mSharedPref.getString("URL", "").toString()


        // Instancia de la clase Shoes
        val shoes = Shoes(shoeName, shoePrice, imgUrl)

        // Lista con los elementos guardados
        val shoesList = ArrayList<Shoes>()
        shoesList.add(shoes)


        initCartShoesAdapter(shoesList)

        binding.btMainFromCart.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_thirdFragment_to_firstFragment)
        }

        return binding.root
    }

    private fun initCartShoesAdapter(shoesList: List<Shoes>) {
        val cartShoesAdapter = CartShoesAdapter(shoesList)

        binding.rvCartShoesList.adapter = cartShoesAdapter
        binding.rvCartShoesList.layoutManager = LinearLayoutManager(requireContext())
    }

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