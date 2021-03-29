package com.tul.carshop.services.shoppingcar

interface ProductsShoppingCarInterface<CL,SP,ID,PR,CUA> {
    fun findAll(): List<SP>
    fun findById(id:ID):SP?
    fun findByClient(cl:CL): List<SP>?
    fun addProduct(cl:CL,pr:PR,cua:CUA): SP
    fun editProduct(cl:CL,pr:PR,cua:CUA): SP
    fun cleanCar(cl:CL):Boolean
    fun deleteProduct(id:ID): SP
}