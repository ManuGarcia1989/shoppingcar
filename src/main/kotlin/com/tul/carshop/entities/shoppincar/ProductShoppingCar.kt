package com.tul.carshop.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

enum class ShoppingCarStates{ PENDING,COMPLETED }

@Entity
class ShoppingCarProduct{
    @Id
    val id: UUID = UUID.randomUUID()
    @OneToOne
    var product: Product? = null
    var cuantity: Int? = null
    var finalPrice : Double? = null
    //
    var user:UUID? = null
    var state: ShoppingCarStates = ShoppingCarStates.PENDING

}