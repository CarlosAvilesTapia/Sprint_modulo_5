package cl.cat2814.sprintmodulo5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cl.cat2814.sprintmodulo5.ShoesInventory.Companion.getPriceFormat
import cl.cat2814.sprintmodulo5.databinding.ItemShoesBinding
import coil.load

class ShoesAdapter : RecyclerView.Adapter<ShoesAdapter.ViewHolder>() {

    var shoes = mutableListOf<Shoes>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesAdapter.ViewHolder {
        val binding = ItemShoesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoesAdapter.ViewHolder, position: Int) {
        val itemShoes = shoes[position]
        holder.bind(itemShoes)

    }

    override fun getItemCount(): Int {
        return shoes.size
    }

    fun setData(shoesInventory: List<Shoes>) {
        this.shoes = shoesInventory.toMutableList()

    }

    class ViewHolder(val binding: ItemShoesBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {



        fun bind(itemShoes: Shoes) {

            binding.ivShoeItem.load(itemShoes.imgUrl)
            binding.tvShoeName.text = itemShoes.name
            binding.tvShoePrice.text = getPriceFormat(itemShoes.price)

            binding.cvShoeItem.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("Url", itemShoes.imgUrl)
                bundle.putString("Name", itemShoes.name)
                bundle.putString("Price", getPriceFormat(itemShoes.price))

                Navigation.findNavController(binding.root).navigate(R.id.action_firstFragment_to_secondFragment, bundle)


            }

        }

        override fun onClick(v: View?) {
         /*   val position = layoutPosition.
            val itemShoes = shoes[position]

            val bundle = Bundle()
            bundle.putString("Url", itemShoes.imgUrl)
            bundle.putString("Name", itemShoes.name)
            bundle.putString("Price", getPriceFormat(itemShoes.price))

            Navigation.findNavController(binding.root).navigate(R.id.action_firstFragment_to_secondFragment, bundle)
*/

        }
    }
}