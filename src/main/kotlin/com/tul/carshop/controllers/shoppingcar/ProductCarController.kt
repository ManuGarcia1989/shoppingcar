package com.tul.carshop.controllers.shoppingcar

import com.tul.carshop.entities.Product
import com.tul.carshop.entities.ShoppingCarProduct
import com.tul.carshop.routes.Router
import com.tul.carshop.services.shoppingcar.ProductsShoppingCarService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(Router.BASE_ROUTE + Router.BASE_CAR)
class ProductCarController(productsShoppingCarService: ProductsShoppingCarService): ProductCarBasicController<UUID,ShoppingCarProduct, UUID,Product,Int>(productsShoppingCarService)