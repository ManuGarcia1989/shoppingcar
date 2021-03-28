package com.tul.carshop

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tul.carshop.entities.Product
import com.tul.carshop.routes.Router
import com.tul.carshop.services.products.ProductService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.junit.Assert
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@RunWith(SpringRunner::class)
@SpringBootTest
class CarshopApplicationTests {

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
		val json = mockMvc.perform(MockMvcRequestBuilders.get(Router.BASE_ROUTE + Router.PRODUCTS))
				.andExpect(status().isOk)
				.andReturn().response.contentAsString

		val products: List<Product> = mapper.readValue(json)

		assertThat(productsFromService, Matchers.`is`(Matchers.equalTo(products)))
	}

}
