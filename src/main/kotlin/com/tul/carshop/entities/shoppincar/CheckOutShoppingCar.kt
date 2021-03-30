package com.tul.carshop.entities.shoppincar

import com.tul.carshop.entities.ShoppingCarProduct
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ShoppingCarCheckOut(
    @Id
    val id: UUID = UUID.randomUUID(),
    val user: UUID,
    val total:Double
    ){
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