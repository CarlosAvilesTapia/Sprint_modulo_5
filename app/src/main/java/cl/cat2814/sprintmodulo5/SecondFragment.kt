package cl.cat2814.sprintmodulo5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import cl.cat2814.sprintmodulo5.databinding.FragmentSecondBinding
import coil.load


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

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
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
        binding.tvShoePriceDetail.text = param3

        binding.btBackToMain.setOnClickListener{
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_secondFragment_to_firstFragment) }
        }


        /*binding.ivShoeItem.load(itemShoes.imgUrl)
            binding.tvShoeName.text = itemShoes.name
            binding.tvShoePrice.text = getPriceFormat(itemShoes.price)*/

        /* Glide.with(getContext()).load(mParam1).into(binding.ivDetail);

        // binding del text view enviado desde el fragment anterior.
        binding.tvDetail.setText(mParam2);

        // Asignación del botón para volver al listado principal.
        binding.backButton.setOnClickListener(v ->
                Navigation.findNavController(getView()).
                navigate(R.id.action_secondFragment_to_firstFragment));*/


        return binding.root
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