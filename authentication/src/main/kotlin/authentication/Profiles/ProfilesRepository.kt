package com.coded.spring.Profiles

import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface ProfilesRepository : JpaRepository<ProfileEntity, Long>