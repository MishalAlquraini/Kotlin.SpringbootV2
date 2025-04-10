package com.coded.spring.ordering

import com.coded.spring.users.UsersRepository
import jakarta.inject.Named


@Named
class OrdersService(
    val usersRepo : UsersRepository,
    val ordersRepo : OrdersRepository,
    val itemsRepository: ItemsRepository
) {
    fun createOrder(userId: Long, items: List<Item>): OrdersEntity {
        val user = usersRepo.findById(userId).get()
        val newOrder = OrdersEntity(user = user)
        val savedOrder = ordersRepo.save(newOrder)
        val newItems = items.map{
            ItemEntity(
                orderId = savedOrder.id!!,
                name = it.name ,
                quantity = it.quantity
            )
        }
        itemsRepository.saveAll(newItems)
        return savedOrder


    }
}