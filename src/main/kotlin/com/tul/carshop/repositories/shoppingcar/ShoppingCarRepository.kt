package com.tul.carshop.repositories.shoppingcar

import com.tul.carshop.entities.ShoppingCarProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ShoppingCarRespository: JpaRepository<ShoppingCarProduct, UUID>
