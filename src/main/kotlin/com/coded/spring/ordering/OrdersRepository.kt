package com.coded.spring.ordering

import com.coded.spring.users.UserEntity
import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface OrdersRepository : JpaRepository<OrdersEntity, Long> {
    fun findByUserId(userId: Long): List<OrdersEntity>
}

@Entity
@Table(name = "orders")
data class OrdersEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    val user: UserEntity,

    @OneToMany(mappedBy = "orderId")
    val items: List<ItemEntity>? = null
){
    constructor() : this(null, UserEntity(), )
}