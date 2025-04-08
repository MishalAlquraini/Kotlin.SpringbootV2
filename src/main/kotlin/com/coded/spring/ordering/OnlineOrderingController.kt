package com.coded.spring.ordering

import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
class OnlineOrderingController(
    val orderRepository: OrderRepository
){

    @GetMapping("/helloOrders")
    fun hello() =
        orderRepository.findAll()


    @PostMapping("/orders")
    // this is the command
    fun addOrder( @RequestBody request: OrderRequest){
        val addUser = Order(
            username = request.user,
            resturant = request.restaurant,
            items = request.items
        )
    }
    @PostMapping("/orders1")
    // this is the same command but we can save it (better)
    fun saveOrders( @RequestBody request: OrderRequest) = orderRepository.save(
        Order(username = request.user,
            resturant = request.restaurant,
            items = request.items)
    )

}

data class OrderRequest(
    val user: String ,
    val restaurant: String ,
    val items: MutableList<String>
)
