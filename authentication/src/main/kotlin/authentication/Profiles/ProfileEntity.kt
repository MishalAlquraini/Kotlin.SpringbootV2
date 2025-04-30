package com.coded.spring.Profiles

import jakarta.persistence.*


@Entity
@Table(name="profiles")
data class ProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userID: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: Long
){
    constructor() : this(null,0, "","",0)
}