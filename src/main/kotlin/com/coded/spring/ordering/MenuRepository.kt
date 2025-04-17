package com.coded.spring.ordering

import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface MenuRepository : JpaRepository<MenuEntity, Long>