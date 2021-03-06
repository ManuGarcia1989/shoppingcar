package com.tul.carshop.configurations

import com.tul.carshop.entities.Product
import com.tul.carshop.entities.ShoppingCarProduct
import com.tul.carshop.services.products.ProductService
import com.tul.carshop.services.shoppingcar.ProductsShoppingCarService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class OnBoot(private val productService: ProductService,private val shoppingCarService: ProductsShoppingCarService):ApplicationRunner {
    override fun run(args: ApplicationArguments?){
        val productOne =Product(sku = UUID.randomUUID(),name = "Martillo for test", description = "Created for Unit Test", price = 777.77, discount = false)
        val productTwo =Product(sku = UUID.randomUUID(),name = "Clavos for test", description = "Created for Unit Test seconod product", price = 777.0, discount = false)
        val clientOne = UUID.randomUUID()
        val clientTwo = UUID.randomUUID()
        listOf(productOne,productTwo).forEach{
            productService.save(it)
        }
        val scProduct1 = ShoppingCarProduct(user = clientOne,product = productOne,cuantity = 2)
        val scProduct2 = ShoppingCarProduct(user = clientTwo,product = productTwo,cuantity = 5)
        shoppingCarService.addProduct(scProduct1)
        shoppingCarService.addProduct(scProduct2)
    }
}