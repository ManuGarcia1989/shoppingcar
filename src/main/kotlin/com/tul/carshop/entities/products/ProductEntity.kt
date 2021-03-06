package com.tul.carshop.entities
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Product(
    @Id
    val sku: UUID = UUID.randomUUID(),
    @Column(unique = true, nullable = false)
    var name: String? = null,
    var description:String? = null,
    
    var price:Double = 0.0,
    var discount: Boolean = false
)
{
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if(other === this) return true
        if(this.javaClass != other.javaClass) return false
        other as Product
        return this.sku == other.sku
    }

    override fun hashCode(): Int {
        return sku.hashCode()
    }
}