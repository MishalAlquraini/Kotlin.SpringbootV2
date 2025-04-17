package com.coded.spring.ordering

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MenuController(
    val menuRepository: MenuRepository,
    val menuService: MenuService
){

    @GetMapping("/public/users/v1/list/menu")
    fun sayUsers() = menuRepository.findAll()


    @PostMapping("/users/v1/create/menu")
    fun menuAdd(@RequestBody request: RequestMenu): MenuEntity {
        val newMenu = menuService.addMenu(request)
        return newMenu
    }

}