package com.coded.spring.ordering

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name="menu")
data class MenuEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
//   @Column(name = "name")
//    val myFieldName: String,
    val name: String,
    val price: BigDecimal
){
    constructor() : this(null,"", BigDecimal.ZERO)
}