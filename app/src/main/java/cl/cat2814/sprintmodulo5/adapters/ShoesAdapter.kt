package cl.cat2814.sprintmodulo5.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cl.cat2814.sprintmodulo5.R
import cl.cat2814.sprintmodulo5.Shoes
import cl.cat2814.sprintmodulo5.ShoesInventory.Companion.getPriceFormat
import cl.cat2814.sprintmodulo5.databinding.ItemShoesBinding
import coil.load

class ShoesAdapter : RecyclerView.Adapter<ShoesAdapter.ViewHolder>() {

    private var shoes = mutableListOf<Shoes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShoesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemShoes = shoes[position]
        holder.bind(itemShoes)
    }

    override fun getItemCount(): Int {
        return shoes.size
    }

    fun setData(shoesInventory: List<Shoes>) {
        this.shoes = shoesInventory.toMutableList()
    }

    class ViewHolder(private val binding: ItemShoesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemShoes: Shoes) {

            binding.tvShoeName.text = itemShoes.name
            binding.tvShoePrice.text = getPriceFormat(itemShoes.price)
            binding.ivShoeItem.load(itemShoes.imgUrl)


            binding.cvShoeItem.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("Name", itemShoes.name)
                bundle.putInt("Price", itemShoes.price!!) // Cambio de string a int.
                bundle.putString("Url", itemShoes.imgUrl)

                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_firstFragment_to_secondFragment, bundle)
            }
        }
    }
}
