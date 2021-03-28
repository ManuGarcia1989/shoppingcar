package com.tul.carshop.services.products

import com.tul.carshop.entities.Product
import com.tul.carshop.repositories.ProductRepository
import com.tul.carshop.utils.update
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class ProductService(private val productRepository: ProductRepository): ProductsBasicCrud<Product, String> {

    override fun  findAll():List<Product> = productRepository.findAll()

    override fun findById(id: String): Product? = this.productRepository.findByIdOrNull(id)

    override fun save(t: Product): Boolean = this.productRepository.save(t).let {
        return true
    }

    override fun update(t: Product): Boolean = this.productRepository.save(t).let { return true }

    override fun deleteById(id: String): Boolean = this.productRepository.deleteById(id).let { return true }
}
