package com.tul.carshop.controllers.products

import com.tul.carshop.routes.Router
import com.tul.carshop.services.products.ProductsBasicCrud
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

abstract class ProductsBasicController<T,ID>(private val basicCrud: ProductsBasicCrud<T, ID>){
    @ApiOperation("Get all products")
    @GetMapping
    fun findAll(): List<T> = basicCrud.findAll()

    @ApiOperation("Find product by id")
    @GetMapping(Router.PRODUCT_BY_ID)
    fun findById(@PathVariable id:ID): ResponseEntity<T> {
        val entity = basicCrud.findById(id)
        return ResponseEntity.status(if(entity!=null) HttpStatus.OK else HttpStatus.NO_CONTENT).body(entity)
    }

    @ApiOperation("Creat new product")
    @PostMapping(Router.PRODUCT_ADD)
    fun save( @RequestBody body: T) = ResponseEntity.status(HttpStatus.CREATED).body(this.basicCrud.save(body))

    @ApiOperation("Modify one product")
    @PutMapping(Router.PRODUCT_PUT)
    fun update(@RequestBody body: T) = basicCrud.update(body)


    @ApiOperation("Delete product by id")
    @DeleteMapping(Router.PRODUCT_DELETE_BY_ID)
    fun deleteById(@PathVariable id: ID) = basicCrud.deleteById(id)

}

