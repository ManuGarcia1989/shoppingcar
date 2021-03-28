package com.tul.carshop.services.products

import com.tul.carshop.entities.Product
import com.tul.carshop.repositories.ProductRepository
import com.tul.carshop.utils.update
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException


@Service
class ProductService(private val productRepository: ProductRepository): ProductsBasicCrud<Product, String> {

    override fun  findAll():List<Product> = productRepository.findAll()

    override fun findById(id: String): Product? = this.productRepository.findByIdOrNull(id)

    override fun save(t: Product): Product {
        return if(!this.productRepository.existsById(t.sku)) this.productRepository.save(t) else throw DuplicateKeyException("${t.name} does exists")
    }

    override fun update(t: Product): Product {
        return if(this.productRepository.existsById(t.sku)) this.productRepository.save(t) else throw EntityNotFoundException("${t.name} does not exists")
    }

    override fun deleteById(id: String):Product{
        return this.findById(id)?.apply {
            this@ProductService.productRepository.deleteById(this.sku)
        }?:throw EntityNotFoundException("$id does not exists")
    }
}
