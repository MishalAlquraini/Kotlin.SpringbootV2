package com.coded.spring.ordering

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ordersController(
    val ordersService: OrdersService,
    val ordersRepository: OrdersRepository,
    val itemsRepository: ItemsRepository
){

    @GetMapping("/orders/v1/list")
    fun showUsers() = ordersRepository.findAll()

    @PostMapping("/orders")
    fun sayOrder( @RequestBody request: OrderRequest)
            = ordersService.createOrder(request.userId, request.items)
}


data class OrderRequest(
    val userId: Long,
    val items: List<Item>
)
data class Item(
    val name: String,
    val quantity : Int
)
