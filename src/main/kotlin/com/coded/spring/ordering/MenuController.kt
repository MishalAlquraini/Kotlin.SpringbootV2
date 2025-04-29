package com.coded.spring.ordering

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MenuController(
    val menuRepository: MenuRepository,
    val menuService: MenuService
){

    @GetMapping("/Public/menu")
    fun sayUsers() = menuService.listMenu()


    @PostMapping("Public/users/v1/create/menu")
    fun menuAdd(@RequestBody requestMenu: RequestMenu): Any {
//        val newMenu = menuService.addMenu(request)
        return ResponseEntity.ok().body(menuService.addMenu(requestMenu))
    }

}