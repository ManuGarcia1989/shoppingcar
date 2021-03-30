package com.tul.carshop.entities.shoppincar

import com.tul.carshop.entities.ShoppingCarProduct
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
data class ShoppingCarCheckOut(
    @Id
    val id: UUID = UUID.randomUUID(),
    val user: UUID,
    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    var products : List<ShoppingCarProduct>? = null,
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