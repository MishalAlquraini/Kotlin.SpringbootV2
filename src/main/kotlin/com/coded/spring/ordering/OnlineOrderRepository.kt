package com.coded.spring.ordering

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface OrderRepository : JpaRepository<Order, String>

@Entity
@Table(name = "usernames")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name="username")
    val username: String,
    val resturant: String,
    @CollectionTable
    val items: MutableList<String>
){
    constructor() : this(null,"", "", mutableListOf())
}