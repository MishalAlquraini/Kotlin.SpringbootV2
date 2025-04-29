package com.coded.spring.ordering

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal

interface ItemsRepository : JpaRepository<ItemEntity, Long>

@Entity
@Table(name="items")
data class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    val orderId : Long,
    val name : String,
    val price : BigDecimal,
    @Column(name = "quentity")
    val quantity : Int
){
    constructor() : this(null, 0, "", BigDecimal.ZERO,0)
}