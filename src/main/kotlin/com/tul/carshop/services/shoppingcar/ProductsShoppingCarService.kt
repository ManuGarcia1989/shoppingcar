package com.tul.carshop.services.shoppingcar

import com.tul.carshop.entities.Product
import com.tul.carshop.entities.ShoppingCarProduct
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductsShoppingCarService: ProductsShoppingCarInterface<String,ShoppingCarProduct, String,Product,String> {
    private val productsCar: MutableSet<ShoppingCarProduct> = mutableSetOf()
    override fun findByClient(cl: String): List<ShoppingCarProduct> {
        val temp : List<ShoppingCarProduct> = mutableListOf()
        for(product in productsCar)
            if(product.user == UUID.fromString(cl))
                temp.toMutableList().add( product)
        return temp
    }


     override fun cleanCar(cl: String): Boolean {
        var response  = false
        for(product in productsCar)
            if(product.user == UUID.fromString(cl)) {
                productsCar.remove(product)
                response = true
            }
        return response
    }

   override fun deleteProduct(id: String): Boolean {
        var response = false
        for(product in productsCar)
            if(product.id == UUID.fromString(id)) {
                productsCar.remove(product)
                response = true
            }
        return response
    }

    override fun addProduct(cl: String, pr: Product, cua: String): Boolean {

        val tempProduct = ShoppingCarProduct()
        tempProduct.cuantity =cua.toInt()
        tempProduct.product = pr
        tempProduct.user = UUID.fromString(cl)
        return true
    }

    override fun editProduct(cl: String, pr: Product, cua: String): Boolean {
        var response = false
        for(product in productsCar)
            if(product.user == UUID.fromString(cl) && product.product == pr)  {
                product.product = pr
                product.cuantity= cua.toInt()
                response = true
            }
        return response
    }
}