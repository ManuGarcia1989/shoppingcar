package com.tul.carshop.ProductsUnitTest

import com.fasterxml.jackson.databind.ObjectMapper
import com.tul.carshop.entities.Product
import com.tul.carshop.routes.Router
import com.tul.carshop.services.products.ProductService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.FixMethodOrder
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ProductsApplicationTests {
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private val mockMvc: MockMvc by lazy {
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print()).build()
    }


    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var productService: ProductService


    @Test
    fun a_saveSuccessfully(){
        val product = Product(
            sku=UUID.fromString("56225443-b3fb-488e-81d3-60c7c96cf701"),
            name= "Taladro from Unit Test",
            description = "Created for Unit Test",
            price = 50000.0,
            discount = false
        )

        val productFromApi:Product = mockMvc.perform(MockMvcRequestBuilders.post(Router.BASE_ROUTE+ Router.PRODUCTS+ Router.PRODUCT_ADD)
            .body(data = product, mapper = mapper))
            .andExpect(status().isCreated)
            .bodyTo(mapper)

        assert(productService.findAll().contains(productFromApi))

    }

    @Test
    fun b_findAll() {
        val productsFromService = productService.findAll()
        val products: List<Product> = mockMvc.perform(MockMvcRequestBuilders.get(Router.BASE_ROUTE + Router.PRODUCTS))
            .andExpect(status().isOk)
            .bodyTo(mapper)

        assertThat(productsFromService, Matchers.`is`(Matchers.equalTo(products)))
    }

    @Test
    fun c_findById(){
        val productFromService = productService.findAll()
        assert(!productFromService.isEmpty()){"Should not be empty"}
        val product = productFromService.last()

        mockMvc.perform(MockMvcRequestBuilders.get(Router.BASE_ROUTE+ Router.PRODUCTS+"/find_by_id/"+product.sku.toString()))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.sku", Matchers.`is`(product.sku.toString())))
    }

    @Test
    fun d_findByIdEmpty(){
        mockMvc.perform(MockMvcRequestBuilders.get(Router.BASE_ROUTE+ Router.PRODUCTS+"/find_by_id"+"/${UUID.randomUUID()}"))
            .andExpect(status().isNoContent)
            .andExpect(jsonPath("$").doesNotExist())
    }



    @Test
    fun e_saveDuplicateEntity(){
        val productsFromService = productService.findAll()
        assert(!productsFromService.isEmpty()){"Should not be empty"}
        val product = productsFromService.last()

        mockMvc.perform(MockMvcRequestBuilders.post(Router.BASE_ROUTE+ Router.PRODUCTS+ Router.PRODUCT_ADD)
            .body(data = product, mapper = mapper))
            .andExpect(status().isConflict)
            .andExpect(jsonPath("$.title",Matchers.`is`("DuplicateKeyException")))
    }

    @Test
    fun f_updateSuccessfully(){
        val productsFromService = productService.findAll()
        assert(!productsFromService.isEmpty()){"Should not be empty"}

        val product = productsFromService.first().copy(price = 5555.55)
        val result:Product = mockMvc.perform(MockMvcRequestBuilders.put(Router.BASE_ROUTE+ Router.PRODUCTS+ Router.PRODUCT_PUT)
            .body(data = product, mapper = mapper))
            .andExpect(status().isOk)
            .bodyTo(mapper)

        assertThat(productService.findById(product.sku), Matchers.`is`(result))


    }
    @Test
    fun f2_updateEntityNotFound(){
        val product = Product(sku= UUID.randomUUID(), name = "Fail Update",description = "none", price = 0.1)
        mockMvc.perform(MockMvcRequestBuilders.put(Router.BASE_ROUTE+ Router.PRODUCTS+ Router.PRODUCT_PUT)
            .body(data = product, mapper = mapper))
            .andExpect(status().isConflict)
            .andExpect(jsonPath("$.title",Matchers.`is`("EntityNotFoundException")))
    }

    @Test
    fun g_delteById(){
        val productsFromService = productService.findAll()
        assert(!productsFromService.isEmpty()){"Should not be empty"}
        val product = productsFromService.last()
        println(product)
        println(product.sku)
        val productFromApi : Product = mockMvc.perform(MockMvcRequestBuilders.delete(Router.BASE_ROUTE+ Router.PRODUCTS+ "/delete_by_id/${product.sku.toString()}"))
            .andExpect(status().isOk)
            .bodyTo(mapper)

        assert(!productService.findAll().contains(productFromApi))
    }

    @Test
    fun h_deleteByIdNotFound(){
       mockMvc.perform(MockMvcRequestBuilders.delete(Router.BASE_ROUTE+ Router.PRODUCTS+ "/delete_by_id/${UUID.randomUUID()}"))
            .andExpect(status().isConflict)
            .andExpect(jsonPath("$.title", Matchers.`is`("EntityNotFoundException")))
    }
}