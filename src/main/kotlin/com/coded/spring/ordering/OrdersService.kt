package com.coded.spring.ordering

import com.coded.spring.users.UsersRepository
import jakarta.inject.Named
import org.springframework.beans.factory.annotation.Value
import java.math.BigDecimal


@Named
class OrdersService(
    val usersRepo : UsersRepository,
    val ordersRepo : OrdersRepository,
    val itemsRepository: ItemsRepository,
    @Value("\${discount.feature}")
    val discount: Boolean
) {
    fun createOrder(userId: Long, items: List<Item>): Order {
        val user = usersRepo.findById(userId).get()
        val newOrder = OrdersEntity(user = user)
        val savedOrder = ordersRepo.save(newOrder)

        val newItems = items.map{
            ItemEntity(
                orderId = savedOrder.id!!,
                name = it.name ,
                price = if (discount)it.price.multiply(BigDecimal(0.8)) else it.price,
                quantity = it.quantity
            )
        }
        val savedItems = itemsRepository.saveAll(newItems)

        return Order(
            userId = savedOrder.id!!,
            items = savedItems.map {
                Item(
                    name = it.name,
                    price = it.price,
                    quantity = it.quantity
                )
            }
        )
    }
//        if (discount){
//            itemsRepository.saveAll(items.map {
//                ItemEntity(
//                    orderId = savedOrder.id!!,
//                    name = it.name,
//                    price = it.price.multiply(BigDecimal(0.8)),
//                    quantity = it.quantity
//
//                )
//            })
//        }else{
//            itemsRepository.saveAll(items.map {
//                ItemEntity(
//                    orderId = savedOrder.id!!,
//                    name = it.name,
//                    price = it.price,
//                    quantity = it.quantity
//
//                )
//            })
//
//        }
}
data class Order(
    val userId: Long,
    val items: List<Item>
)