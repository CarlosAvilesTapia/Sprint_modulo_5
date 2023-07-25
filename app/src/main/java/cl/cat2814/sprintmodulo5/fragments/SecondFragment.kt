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


// Parámetros para recibir datos del first fragment.
private const val ARG_PARAM1 = "Name"
private const val ARG_PARAM2 = "Price"
private const val ARG_PARAM3 = "Url"

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var mSharedPref: SharedPreferences
    private lateinit var gson: Gson
    private lateinit var shoesList: MutableList<Shoes>
    private val bundle = Bundle()

    private var param1: String? = null
    private var param2: Int? = 0 // Se cambia a Int
    private var param3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)// Se cambia a Int
            param3 = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)

        binding.tvShoeNameDetail.text = param1
        // Se aplica la función para formato de precio.
        binding.tvShoePriceDetail.text = ShoesInventory.getPriceFormat(param2)
        binding.ivShoeDetail.load(param3)


        mSharedPref = requireActivity().getSharedPreferences("ShoesPreferences", Context.MODE_PRIVATE)

        gson = Gson()

        shoesList = getList()

        initListeners()

        return binding.root
    }

    private fun initListeners() {

        // Botón agregar al carrito.
        binding.btAddToCart.setOnClickListener {

            val shoesName = param1.toString()
            val shoesPrice = param2.toString().toInt()
            val imgUrl = param3.toString()

            // Llenado del listado.
            val shoes = Shoes(shoesName, shoesPrice, imgUrl)
            shoesList.add(shoes)

            val jsonString = gson.toJson(shoesList)
            mSharedPref.edit().putString("Shoes list", jsonString).apply()

            Toast.makeText(requireContext(),"Producto agregado exitosamente", Toast.LENGTH_SHORT).show()

        }

        // Botón para volver al comienzo.
        binding.btBackToMain.setOnClickListener{
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_secondFragment_to_firstFragment) }
        }

        // Botón para ir al carrito.
        binding.btGoToCart.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_secondFragment_to_thirdFragment, bundle)
        }
    }

    // Función que obtiene los elementos de la lista.
    private fun getList(): MutableList<Shoes> {
        val jsonString = mSharedPref.getString("Shoes list", null)
        val listType = object : TypeToken<MutableList<Shoes>>() {}.type
        return gson.fromJson(jsonString, listType) ?: mutableListOf()
    }
}
