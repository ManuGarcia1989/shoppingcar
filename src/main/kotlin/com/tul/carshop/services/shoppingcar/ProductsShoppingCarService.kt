package com.tul.carshop.services.shoppingcar

import com.tul.carshop.entities.Product
import com.tul.carshop.entities.ShoppingCarProduct
import com.tul.carshop.entities.ShoppingCarStates
import com.tul.carshop.entities.shoppincar.ShoppingCarCheckOut
import com.tul.carshop.repositories.shoppingcar.ShoppingCarRespository
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class ProductsShoppingCarService(private val shoppingCarProductRepo: ShoppingCarRespository): ProductsShoppingCarInterface<String,ShoppingCarProduct, String,Product,Int> {

    override fun findAll(): List<ShoppingCarProduct> = this.shoppingCarProductRepo.findAll()

    override fun findById(id:String): ShoppingCarProduct? = this.shoppingCarProductRepo.findByIdOrNull(UUID.fromString(id))

    override fun findByClient(client: String): List<ShoppingCarProduct>{
        val all = this.shoppingCarProductRepo.findAll()
        var tempList:List<ShoppingCarProduct> = emptyList()
        for(sc in all){
            if(sc.user== UUID.fromString(client))
                tempList += sc
        }

        return if(tempList.size > 0) tempList else throw  EntityNotFoundException("$client does not exists")
    }


     override fun cleanCar(client: String): Boolean {
         val all = this.findAll()
         var response = false
         for(product in all)
            if(product.user == UUID.fromString(client)) {
                this@ProductsShoppingCarService.shoppingCarProductRepo.deleteById(product.id)
                response = true
            }
         return if(response) response else throw EntityNotFoundException("$client does not exists")
    }

   override fun deleteProduct(id: String): ShoppingCarProduct {
       return this.findById(id)?.apply {
           this@ProductsShoppingCarService.shoppingCarProductRepo.deleteById(this.id)
       } ?: throw EntityNotFoundException("$id does not exists")
    }

    override fun addProduct(shoppingCarProduct: ShoppingCarProduct): ShoppingCarProduct{
        val clientProducts: List<ShoppingCarProduct> = shoppingCarProductRepo.findAll()
        println("Empieza servicio")
        var exist = false
        for(shoppingCarProduct_ in clientProducts){
            if(shoppingCarProduct_.user == shoppingCarProduct.user && shoppingCarProduct_.product == shoppingCarProduct.product){
                exist = true
            }
        }
        if(!exist) {
            val finalPrice = shoppingCarProduct.product.price * shoppingCarProduct.cuantity
            if(shoppingCarProduct.product.discount) finalPrice/2.0
            return this.shoppingCarProductRepo.save(shoppingCarProduct)
        } else throw DuplicateKeyException("${shoppingCarProduct.product.name} does in shopping car from ${shoppingCarProduct.user.toString()}")

    }

    override fun editProduct(shoppingCarProduct: ShoppingCarProduct): ShoppingCarProduct {
        val clientProducts = this.findByClient(shoppingCarProduct.user.toString()).orEmpty()
        var exist = false
        for(shoppingCarProduct_ in clientProducts){
            if(shoppingCarProduct_.user == shoppingCarProduct.user && shoppingCarProduct_.product == shoppingCarProduct.product){
                exist = true
            }
        }
        if(exist) {
            return this.shoppingCarProductRepo.save(shoppingCarProduct)
        }
        else throw EntityNotFoundException("${shoppingCarProduct.product.name} does not in shopping car from ${shoppingCarProduct.user.toString()}")
    }

    override fun checkOut(client: String): ShoppingCarCheckOut {
        val clientProducts: List<ShoppingCarProduct> = this.findByClient(client)
        var TotalPrice:Double = 0.0
        for(clientProduct in clientProducts){
            clientProduct.state = ShoppingCarStates.COMPLETED
            TotalPrice += clientProduct.finalPrice!!
            this.shoppingCarProductRepo.save(clientProduct)
        }
        val response = ShoppingCarCheckOut( products = clientProducts ,total = TotalPrice, user = UUID.fromString(client) )
        return if(clientProducts.size > 0 ) response else throw  EntityNotFoundException("$client does not exists")
    }


}