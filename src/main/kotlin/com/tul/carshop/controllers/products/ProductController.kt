package com.tul.carshop.controllers.products

import com.tul.carshop.entities.Product
import com.tul.carshop.routes.Router
import com.tul.carshop.services.products.ProductService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(Router.BASE_ROUTE + Router.PRODUCTS)
class ProductController(productService: ProductService): ProductsBasicController<Product, UUID>(productService)
