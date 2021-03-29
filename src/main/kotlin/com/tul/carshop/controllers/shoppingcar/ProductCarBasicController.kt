package com.tul.carshop.controllers.shoppingcar

import com.tul.carshop.routes.Router
import com.tul.carshop.services.shoppingcar.ProductsShoppingCarInterface
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

abstract class ProductCarBasicController <CL,SP,ID,PR,CUA>(private val crudProductsCar: ProductsShoppingCarInterface<CL,SP,ID,PR,CUA> ){
    @ApiOperation("list all products for all clients")
    @GetMapping(Router.SHOW_ALL_PRODUCTS_FOR_ALL_CLIENTS)
    fun findAll() = crudProductsCar.findAll()

    @ApiOperation("Find product in shopping car stock")
    @GetMapping(Router.SHOW_BY_ID)
    fun findById(@PathVariable id:ID) = crudProductsCar.findById(id)

    @ApiOperation("Get products from client")
    @GetMapping(Router.LIST_BY_CLIENT)
    fun findByClient(@PathVariable cl:CL) = crudProductsCar.findByClient(cl)

    @ApiOperation("Add product to client shopping car")
    @PostMapping(Router.ADD_TO_CAR)
    fun addProduct(@RequestBody cl: CL, @RequestBody pr:PR, @RequestBody cua:CUA) = crudProductsCar.addProduct(cl,pr,cua)

    @ApiOperation("Edit product from client shopping car")
    @PutMapping(Router.EDIT_CAR)
    fun editProduct(@PathVariable cl: CL, @RequestBody pr:PR, @RequestBody cua:CUA) = crudProductsCar.editProduct(cl,pr,cua)

    @ApiOperation("Clean client shopping car")
    @DeleteMapping(Router.CLEAN_CAR)
    fun cleanCar(@PathVariable cl:CL) = crudProductsCar.cleanCar(cl)

    @ApiOperation("Delete product from client shopping car")
    @DeleteMapping(Router.DELETE_PRODUCT_CAR)
    fun deleteProduct(@PathVariable id:ID) = crudProductsCar.deleteProduct(id)
}