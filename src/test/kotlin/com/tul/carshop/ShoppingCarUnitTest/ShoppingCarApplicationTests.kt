package com.tul.carshop.ShoppingCarUnitTest

import com.fasterxml.jackson.databind.ObjectMapper
import com.tul.carshop.ProductsUnitTest.body
import com.tul.carshop.ProductsUnitTest.bodyTo
import com.tul.carshop.entities.ShoppingCarProduct
import com.tul.carshop.entities.shoppincar.ShoppingCarCheckOut
import com.tul.carshop.routes.Router
import com.tul.carshop.services.shoppingcar.ProductsShoppingCarService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ShoppingCarApplicationTests {
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private val mockMvc: MockMvc by lazy {
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print()).build()
    }


    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var shoppingCarService: ProductsShoppingCarService

    @Test
    fun a0_CheckoutByClientId(){
        val productsFromService = shoppingCarService.findAll()
        assert(!productsFromService.isEmpty()){"Should not be empty"}
        val shoppingCarProduct = productsFromService.first()

        val checkOut: ShoppingCarCheckOut = mockMvc.perform(MockMvcRequestBuilders.put(Router.BASE_ROUTE+ Router.BASE_CAR+"/checkout/"+shoppingCarProduct.user))
            .andExpect(status().isOk)
            .bodyTo(mapper)

        assert(checkOut != null)
    }

    @Test
    fun a9_findAll(){
        val productsFromService = shoppingCarService.findAll()
        val products: List<ShoppingCarProduct> = mockMvc.perform(MockMvcRequestBuilders.get(Router.BASE_ROUTE + Router.BASE_CAR+Router.SHOW_ALL_PRODUCTS_FOR_ALL_CLIENTS))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .bodyTo(mapper)

        assertThat(productsFromService, Matchers.`is`(Matchers.equalTo(products)))
    }

    @Test
    fun b_showClientShoppingCar(){
        val productsFromService = shoppingCarService.findAll()
        assert(!productsFromService.isEmpty()){"Should not be empty"}
        val shoppingCarProduct = productsFromService.first()

        val products: List<ShoppingCarProduct> = mockMvc.perform(MockMvcRequestBuilders.get(Router.BASE_ROUTE+ Router.BASE_CAR+"/"+shoppingCarProduct.user))
            .andExpect(status().isOk)
            .bodyTo(mapper)

        assertThat(shoppingCarProduct.user, Matchers.`is`(Matchers.equalTo(products.last().user)))
    }

    @Test
    fun c_findShoppingCarProductById(){
        val productsFromService = shoppingCarService.findAll()
        assert(!productsFromService.isEmpty()){"Should not be empty"}
        val shoppingCarProduct = productsFromService.last()
        mockMvc.perform(MockMvcRequestBuilders
            .get(Router.BASE_ROUTE+ Router.BASE_CAR+"/find_by_id/${ shoppingCarProduct.id}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id", Matchers.`is`(shoppingCarProduct.id.toString())))

    }

    @Test
    fun b2_addShoppingCarProduct() {
        val productsFromService = shoppingCarService.findAll()
        assert(!productsFromService.isEmpty()){"Should not be empty"}
        val shoppingCarProduct = ShoppingCarProduct(
            user = UUID.randomUUID(),
            cuantity = 4,
            product = productsFromService.first().product
        )

        val productFromApi: ShoppingCarProduct = mockMvc.perform(MockMvcRequestBuilders.post(Router.BASE_ROUTE+ Router.BASE_CAR+ Router.ADD_TO_CAR)
            .body(data = shoppingCarProduct, mapper = mapper))
            .andExpect(status().isOk)
            .bodyTo(mapper)

        assert(shoppingCarService.findAll().contains(productFromApi))
    }

    @Test
    fun c_editProductShoppingCarByIDClient(){
        val productsFromService = shoppingCarService.findAll()
        assert(!productsFromService.isEmpty()){"Should not be empty"}
        val shoppingCarProduct = productsFromService.first().copy(cuantity= 10)

        val result: ShoppingCarProduct = mockMvc.perform(MockMvcRequestBuilders.put(Router.BASE_ROUTE+ Router.BASE_CAR+"/edit_product_car/${shoppingCarProduct.user}")
            .body(data = shoppingCarProduct, mapper = mapper))
            .andExpect(status().isOk)
            .bodyTo(mapper)


        assertThat(result.id, Matchers.`is`(shoppingCarProduct.id))
    }

    @Test
    fun d_deleteClientShoppingCarProductById(){
        val productsFromService = shoppingCarService.findAll()
        assert(!productsFromService.isEmpty()){"Should not be empty"}
        val shoppingCarProduct = productsFromService.first()

        val productFromApi: ShoppingCarProduct = mockMvc.perform(MockMvcRequestBuilders.delete(Router.BASE_ROUTE+ Router.BASE_CAR+ "/delete_product/${shoppingCarProduct.id}"))
            .andExpect(status().isOk)
            .bodyTo(mapper)

        assert(!shoppingCarService.findAll().contains(productFromApi))
    }

    @Test
    fun e_cleanCarByClientId(){
        val productsFromService = shoppingCarService.findAll()
        assert(!productsFromService.isEmpty()){"Should not be empty"}
        val shoppingCarProduct = productsFromService.last()

        val productFromApi: Boolean = mockMvc.perform(MockMvcRequestBuilders.delete(Router.BASE_ROUTE+ Router.BASE_CAR+ "/clean_car/${shoppingCarProduct.user}"))
            .andExpect(status().isOk)
            .bodyTo(mapper)

        assert(productFromApi)
    }

}