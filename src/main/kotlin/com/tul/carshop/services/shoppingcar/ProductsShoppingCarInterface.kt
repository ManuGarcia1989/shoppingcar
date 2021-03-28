package com.tul.carshop.services.shoppingcar

interface ProductsShoppingCarInterface<CL,SP,ID,PR,CUA> {
    fun findByClient(cl:CL): List<SP>
    fun addProduct(cl:CL,pr:PR,cua:CUA): Boolean
    fun editProduct(cl:CL,pr:PR,cua:CUA): Boolean
    fun cleanCar(cl:CL):Boolean
    fun deleteProduct(id:ID): Boolean
}