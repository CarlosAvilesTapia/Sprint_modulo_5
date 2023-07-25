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
import cl.cat2814.sprintmodulo5.R
import cl.cat2814.sprintmodulo5.Shoes
import cl.cat2814.sprintmodulo5.ShoesInventory
import cl.cat2814.sprintmodulo5.databinding.FragmentSecondBinding
import coil.load
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "Url"
private const val ARG_PARAM2 = "Name"
private const val ARG_PARAM3 = "Price"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondBinding
    lateinit var mSharedPref: SharedPreferences
    lateinit var gson: Gson
    lateinit var shoesList: MutableList<Shoes>
    val bundle = Bundle()




    // TODO: Rename and change types of parameters
    var param1: String = ""
    var param2: String = ""
    var param3: Int = 0 // Se cambia a Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1).toString()
            param2 = it.getString(ARG_PARAM2).toString()
            param3 = it.getInt(ARG_PARAM3) // Se cambia a Int
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)


        binding.ivShoeDetail.load(param1)
        binding.tvShoeNameDetail.text = param2

        // Se aplica la función para formato de precio.
        binding.tvShoePriceDetail.text = ShoesInventory.getPriceFormat(param3)

        mSharedPref = requireActivity().getSharedPreferences("ShoesPreferences", Context.MODE_PRIVATE)

        gson = Gson()

        shoesList = getList()

        // Botón agregar al carrito.
        binding.btAddToCart.setOnClickListener {

            // val imgUrl = binding.ivShoeDetail.load(param1).toString()
            val imgUrl = param1

            //val shoesName = binding.tvShoeNameDetail.text.toString()
            val shoesName = param2

            //val shoesPrice = binding.tvShoePriceDetail.text.toString().toInt()
            val shoesPrice = param3

            // Llenado del listado.
            val shoes = Shoes(imgUrl, shoesPrice, shoesName)
            shoesList.add(shoes)


            mSharedPref =requireContext().getSharedPreferences("ShoesPreferences", Context.MODE_PRIVATE)
            gson = Gson()

            val jsonString = gson.toJson(shoesList)
            mSharedPref.edit().putString("Shoes list", jsonString).apply()

            Toast.makeText(requireContext(),"Producto agregado exitosamente?", Toast.LENGTH_SHORT).show()

        }


        binding.btBackToMain.setOnClickListener{
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_secondFragment_to_firstFragment) }
        }


        binding.btGoToCart.setOnClickListener {
            Navigation.findNavController(binding.root)
                    .navigate(R.id.action_secondFragment_to_thirdFragment, bundle)
        }


        return binding.root

    }

    private fun getList(): MutableList<Shoes> {
        val jsonString = mSharedPref.getString("Shoes list", null)
        val listType = object : TypeToken<MutableList<Shoes>>() {}.type
        return gson.fromJson(jsonString, listType) ?: mutableListOf()


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}