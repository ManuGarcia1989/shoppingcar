package com.tul.carshop.entities

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

enum class ShoppingCarStates{ PENDING,COMPLETED }

@Entity
data class ShoppingCarProduct(
    @Id
    val id: UUID = UUID.randomUUID(),
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    var product: Product,
    var cuantity: Int = 0,
    var finalPrice : Double? = 0.0,
    //@ForeignKey
    var user:UUID,
    var state: ShoppingCarStates? = ShoppingCarStates.PENDING
    )
{

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if(other === this) return true
        if(this.javaClass != other.javaClass) return false
        other as ShoppingCarProduct
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}