package com.coded.spring.ordering

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class ordersController(
    val ordersService: OrdersService,
    val ordersRepository: OrdersRepository,
    val itemsRepository: ItemsRepository
){

    @GetMapping("Public/orders/v1/list")
    fun showUsers() = ordersRepository.findAll()

    @PostMapping("Public/orders")
    fun sayOrder( @RequestBody request: OrderRequest) : OrderResponse {
        val result = ordersService.createOrder(request.userId, request.items)
        // return OrderResponse(result.userId, result.items) both ways r correct
        return result.let{
            OrderResponse(
                userId = it.userId,
                items = it.items
            )
        }
    }
}


data class OrderRequest(
    val userId: Long,
    val items: List<Item>
)
data class OrderResponse(
    val userId: Long,
    val items: List<Item>
)

data class Item(
    val name: String,
    val quantity : Int,
    val price: BigDecimal
)
