package cl.cat2814.sprintmodulo5.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.cat2814.sprintmodulo5.Shoes
import cl.cat2814.sprintmodulo5.ShoesInventory.Companion.getPriceFormat
import cl.cat2814.sprintmodulo5.databinding.ItemShoesCartBinding
import coil.load

class CartShoesAdapter: RecyclerView.Adapter<CartShoesAdapter.ViewHolder>() {

    var cartShoes = mutableListOf<Shoes>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartShoesAdapter.ViewHolder {
        val binding = ItemShoesCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartShoesAdapter.ViewHolder, position: Int) {
        val itemShoesCart = cartShoes[position]
        holder.bind(itemShoesCart)
    }

    override fun getItemCount(): Int {
       return cartShoes.size
    }

    fun setData(shoesInventory: List<Shoes>) {
        cartShoes = shoesInventory.toMutableList()
    }

    inner class ViewHolder(val binding: ItemShoesCartBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(itemShoesCart: Shoes) {

            binding.tvCartShoeName.text = itemShoesCart.name
            binding.tvCartShoePrice.text = getPriceFormat(itemShoesCart.price)
            binding.ivCartShoeItem.load(itemShoesCart.imgUrl)

            binding.ibtDeleteItem.setOnClickListener {
                cartShoes.remove(itemShoesCart)
                notifyDataSetChanged()
            }
        }
    }
}
