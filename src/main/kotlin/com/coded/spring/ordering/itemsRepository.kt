package com.coded.spring.ordering

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

interface ItemsRepository : JpaRepository<ItemEntity, Long>

@Entity
@Table(name="items")
data class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    val orderId : Long,
    val name : String,
    @Column(name = "quentity")
    val quantity : Int
){
    constructor() : this(null, 0, "", 0)
}