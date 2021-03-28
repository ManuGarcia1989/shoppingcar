package com.tul.carshop.controllers.shoppingcar

import com.tul.carshop.routes.Router
import com.tul.carshop.services.shoppingcar.ProductsShoppingCarInterface
import org.springframework.web.bind.annotation.*

abstract class ProductCarBasicController <CL,SP,ID,PR,CUA>(private val crudProductsCar: ProductsShoppingCarInterface<CL,SP,ID,PR,CUA> ){
    @GetMapping(Router.LIST_BY_CLIENT)
    fun findByClient(@PathVariable cl:CL) = crudProductsCar.findByClient(cl)

    @PostMapping(Router.ADD_TO_CAR)
    fun addProduct(@PathVariable cl: CL,@RequestBody pr:PR ,@RequestBody cua:CUA) = crudProductsCar.addProduct(cl,pr,cua)

    @PutMapping(Router.EDIT_CAR)
    fun editProduct(@PathVariable cl: CL, @RequestBody pr:PR, @RequestBody cua:CUA) = crudProductsCar.editProduct(cl,pr,cua)

    @DeleteMapping(Router.CLEAN_CAR)
    fun cleanCar(@PathVariable cl:CL) = crudProductsCar.cleanCar(cl)

    @DeleteMapping(Router.DELETE_PRODUCT_CAR)
    fun deleteProduct(@PathVariable id:ID) = crudProductsCar.deleteProduct(id)
}