package cl.cat2814.sprintmodulo5

import android.view.LayoutInflater
import android.view.ViewGroup
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

    class ViewHolder(val binding: ItemShoesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemShoes: Shoes) {

            binding.ivShoeItem.load(itemShoes.imgUrl)
            binding.tvShoeName.text = itemShoes.name
            binding.tvShoePrice.text = getPriceFormat(itemShoes.price)

        }
    }
}