package com.tul.carshop

import com.fasterxml.jackson.databind.ObjectMapper
import com.tul.carshop.entities.Product
import com.tul.carshop.routes.Router
import com.tul.carshop.services.products.ProductService
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
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
    fun findAll() {
        val productsFromService = productService.findAll()
        val products: List<Product> = mockMvc.perform(MockMvcRequestBuilders.get(Router.BASE_ROUTE + Router.PRODUCTS))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .bodyTo(mapper)

        MatcherAssert.assertThat(productsFromService, Matchers.`is`(Matchers.equalTo(products)))
    }

    @Test
    fun findById(){
        val productFromService = productService.findAll()
        assert(!productFromService.isEmpty()){"Should not be empty"}
        val product = productFromService.first()

        mockMvc.perform(MockMvcRequestBuilders.get(Router.BASE_ROUTE+ Router.PRODUCTS+"/find_by_id"+"/${product.sku}"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.sku", Matchers.`is`(product.sku)))
    }

    @Test
    fun findByEmpty(){
        mockMvc.perform(MockMvcRequestBuilders.get(Router.BASE_ROUTE+ Router.PRODUCTS+"/find_by_id"+"/${UUID.randomUUID()}"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist())
    }

    @Test
    fun saveSuccessfully(){
        val product = Product(
            //sku="76225773-bbfb-488e-81b3-60c7c96cf701",
            name= "Martillo",
            description = "Created Test",
            price = 50000.0,
            discount = false
        )

        val result:Boolean = mockMvc.perform(MockMvcRequestBuilders.post(Router.BASE_ROUTE+ Router.PRODUCTS+ Router.PRODUCT_ADD)
            .content(mapper.writeValueAsBytes(product)).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .bodyTo(mapper)
        assert(result)
    }

}