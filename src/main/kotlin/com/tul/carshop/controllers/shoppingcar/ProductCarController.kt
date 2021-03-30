package com.tul.carshop.controllers.shoppingcar

import com.tul.carshop.entities.Product
import com.tul.carshop.entities.ShoppingCarProduct
import com.tul.carshop.routes.Router
import com.tul.carshop.services.shoppingcar.ProductsShoppingCarInterface
import com.tul.carshop.services.shoppingcar.ProductsShoppingCarService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Router.BASE_ROUTE + Router.BASE_CAR)
class ProductCarController (private val shoppingCarService: ProductsShoppingCarService) :
    ProductsShoppingCarInterface<String, ShoppingCarProduct, String, Product, Int>{

    @GetMapping(Router.SHOW_ALL_PRODUCTS_FOR_ALL_CLIENTS)
    override fun findAll():List<ShoppingCarProduct>{
        return shoppingCarService.findAll()
    }


    @ApiOperation("Find product in shopping car stock")
    @GetMapping(Router.SHOW_BY_ID)
    override fun findById(@PathVariable id: String): ShoppingCarProduct? {
        return shoppingCarService.findById(id)
    }

    @ApiOperation("Get products from client")
    @GetMapping(Router.LIST_BY_CLIENT)
    override fun findByClient(@PathVariable client: String) :List<ShoppingCarProduct>?{
        return shoppingCarService.findByClient(client)
    }

    @ApiOperation("Add product to client shopping car")
    @PostMapping(Router.ADD_TO_CAR)
    override fun addProduct(@RequestBody shoppingCarProduct: ShoppingCarProduct) : ShoppingCarProduct{
        return shoppingCarService.addProduct(shoppingCarProduct)
    }

    @ApiOperation("Edit product from client shopping car")
    @PutMapping(Router.EDIT_CAR)
    override fun editProduct(@RequestBody shoppingCarProduct: ShoppingCarProduct) : ShoppingCarProduct{
        return shoppingCarService.editProduct(shoppingCarProduct)
    }

    @ApiOperation("Clean client shopping car")
    @DeleteMapping(Router.CLEAN_CAR)
    override fun cleanCar(@PathVariable client: String):Any {
        return shoppingCarService.cleanCar(client)
    }

    @ApiOperation("Delete product from client shopping car")
    @DeleteMapping(Router.DELETE_PRODUCT_CAR)
    override fun deleteProduct(@PathVariable id:String) : ShoppingCarProduct{
        return shoppingCarService.deleteProduct(id)
    }

}