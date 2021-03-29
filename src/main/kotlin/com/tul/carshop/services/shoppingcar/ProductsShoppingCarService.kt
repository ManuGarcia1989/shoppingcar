package com.tul.carshop.services.shoppingcar

import com.tul.carshop.entities.Product
import com.tul.carshop.entities.ShoppingCarProduct
import com.tul.carshop.repositories.shoppingcar.ShoppingCarRespository
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class ProductsShoppingCarService(private val shoppingCarRespository: ShoppingCarRespository): ProductsShoppingCarInterface<UUID,ShoppingCarProduct, UUID,Product,Int> {

    override fun findAll(): List<ShoppingCarProduct> {
        return this.shoppingCarRespository.findAll()
    }

    override fun findById(id:UUID): ShoppingCarProduct? = this.shoppingCarRespository.findByIdOrNull(id)

    override fun findByClient(cl: UUID): List<ShoppingCarProduct>?{
        val all = this.findAll()
        var tempList:MutableList<ShoppingCarProduct> = mutableListOf()
        for(sc in all){
            if(sc.user==cl)
                tempList.add(sc)
        }
        return tempList
    }


     override fun cleanCar(cl: UUID): Boolean {
         var response  = false
         val all = this.findAll()
         for(product in all)
            if(product.user == cl) {
                this@ProductsShoppingCarService.shoppingCarRespository.deleteById(product.id)
                response = true
            }
         return response
    }

   override fun deleteProduct(id: UUID): ShoppingCarProduct {
       return this.findById(id)?.apply {
           this@ProductsShoppingCarService.shoppingCarRespository.deleteById(this.id)
       } ?: throw EntityNotFoundException("$id does not exists")
    }

    override fun addProduct(cl: UUID, pr: Product, cua: Int): ShoppingCarProduct{
        val clientProducts: List<ShoppingCarProduct>? = this.findByClient(cl)
        var exist = false
        if (clientProducts != null) {
            if(clientProducts.isNotEmpty())
                for(shoppingCarProduct in clientProducts){
                    //if(shoppingCarProduct.product.sku == pr.sku){
                        exist = true
                    //}
                }
        }
        if(!exist) {
            var finalPrice = pr.price * cua
            if(pr.discount) finalPrice/2.0
            val objTemp = ShoppingCarProduct(user = cl,product = pr,cuantity = cua, finalPrice = finalPrice)
            println("Creadoooooooooooooooo")
            return this.shoppingCarRespository.save(objTemp)
        } else {
            println("No Creadoooooooooooooooo")
            return throw DuplicateKeyException("${pr.name} does in shopping car from ${cl.toString()}")

        }
    }

    override fun editProduct(cl: UUID, pr: Product, cua: Int): ShoppingCarProduct {
        val clientProducts = this.findByClient(cl).orEmpty()
        var exist = false
        for(shoppingCarProduct in clientProducts){
            //if(shoppingCarProduct.product.sku == pr.sku){
                exist = true
            //}
        }
        if(exist) {
            var finalPrice = pr.price * cua
            if(pr.discount) finalPrice/2.0
            val objTemp = ShoppingCarProduct(user = cl,product = pr,cuantity = cua, finalPrice = finalPrice)
            return this.shoppingCarRespository.save(objTemp)
        }
        else throw DuplicateKeyException("${pr.name} does not in shopping car from ${cl.toString()}")
    }


}