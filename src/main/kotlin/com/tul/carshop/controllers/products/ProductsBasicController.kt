package com.tul.carshop.controllers.products

import com.tul.carshop.routes.Router
import com.tul.carshop.services.products.ProductsBasicCrud
import org.springframework.web.bind.annotation.*

abstract class ProductsBasicController<T,ID>(private val basicCrud: ProductsBasicCrud<T, ID>){
    @GetMapping
    fun findAll() = basicCrud.findAll()

    @GetMapping(Router.PRODUCT_BY_ID)
    fun findById(@PathVariable id:ID) = basicCrud.findById(id)

    @PostMapping(Router.PRODUCT_ADD)
    fun save(@RequestBody body: T) = basicCrud.save(body)

    @PutMapping(Router.PRODUCT_PUT)
    fun update(@RequestBody body: T) = basicCrud.update(body)

    @DeleteMapping(Router.PRODUCT_DELETE_BY_ID)
    fun deleteById(@PathVariable id: ID) = basicCrud.deleteById(id)
}

