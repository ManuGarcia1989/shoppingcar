package com.tul.carshop.services.products

interface ProductsBasicCrud<T, ID>{
    fun findAll(): List<T>
    fun findById(id:ID): T?
    fun save(t:T): Boolean
    fun update(t:T): Boolean
    fun deleteById(id:ID): Boolean
}