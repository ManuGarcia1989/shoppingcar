package com.tul.carshop.services.shoppingcar

import com.tul.carshop.entities.shoppincar.ShoppingCarCheckOut

interface ProductsShoppingCarInterface<CLIENT,SP_PRODUCT,ID,PRODUCT,CUANTITY> {
    fun findAll(): List<SP_PRODUCT>
    fun findById(id:ID):SP_PRODUCT?
    fun findByClient(client:CLIENT): List<SP_PRODUCT>?
    fun addProduct(shoppingCarProduct : SP_PRODUCT): SP_PRODUCT
    fun editProduct(shoppingCarProduct : SP_PRODUCT): SP_PRODUCT
    fun cleanCar(client:CLIENT): Boolean
    fun deleteProduct(id:ID): SP_PRODUCT
    fun checkOut(client: CLIENT): ShoppingCarCheckOut
}