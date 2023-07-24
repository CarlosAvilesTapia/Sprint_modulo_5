package cl.cat2814.sprintmodulo5

import android.icu.text.NumberFormat
import java.util.Locale

data class Shoes(
    val name: String,
    val price: Int,
    val imgUrl: String
)

class ShoesInventory {
    companion object {
        fun getShoesInventory(): List<Shoes> {
            val shoesInventory = mutableListOf<Shoes>()
            shoesInventory.add(Shoes("Botas Gokú", 100000,"https://d2rbvftet3l7m9.cloudfront.net/media/catalog/product/cache/fbb7b365d63f6fa1a10e51823f808169/u/0/u0o7808rthj.jpg"))
            shoesInventory.add(Shoes("Botas Vegeta", 150000,"https://i.pinimg.com/1200x/ef/2c/f4/ef2cf4be253024c2506fcc807100e4a8.jpg"))
            shoesInventory.add(Shoes("Botas Imperio", 200000,"https://i.ebayimg.com/images/g/r~QAAOSw9E5hGfY3/s-l1200.jpg"))
            shoesInventory.add(Shoes("Botas Darth Vader", 300000,"https://m.media-amazon.com/images/I/610hlcDGE+L._AC_UF894,1000_QL80_.jpg"))
            shoesInventory.add(Shoes("Botas Batman", 250000,"https://images.propstore.com/900630.jpg"))
            shoesInventory.add(Shoes("Botas Superman", 50000,"https://www.julienslive.com/images/lot/1270/127008_xl.jpg"))
            shoesInventory.add(Shoes("Botas Spiderman", 70000,"https://www.carnivalstore.co.uk/wp-content/uploads/2022/04/Spiderman-Child-Boot-Covers_19051_img.jpg"))
            shoesInventory.add(Shoes("Zapatos Michael Jackson", 120000,"https://media.cnn.com/api/v1/images/stellar/prod/180421161537-michael-jackson-moonwalk-shoes.jpg"))
            shoesInventory.add(Shoes("Nike Assassin", 140000,"https://purodiseno.lat/wp-content/uploads/2023/04/ZAPATILLAS-SIMPSONS-4-1024x718.png"))
            shoesInventory.add(Shoes("Nike 'Back to the future'", 200000,"https://o.aolcdn.com/hss/storage/midas/f47e67f6910889f436f2a0a2242e1134/202841652/nikemags2015.png.cf.jpg"))

            return shoesInventory
        }

        fun getPriceFormat(price: Int): String {
            val currency: NumberFormat = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
            return currency.format(price)
        }
    }
}