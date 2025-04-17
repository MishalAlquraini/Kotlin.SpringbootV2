package com.coded.spring.ordering

import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class MenuService(
    val menuRepository: MenuRepository
){
    fun addMenu(request: RequestMenu) : MenuEntity{
        val newMenu = MenuEntity(
            name = request.menuName,
            price = request.price
        )
        return menuRepository.save(newMenu)
    }

    fun listMenu(): List<Menu> = menuRepository.findAll().map {
        Menu(
            menuName = it.name,
            price = it.price
        )
    }

}




data class Menu(
    val menuName: String,
    val price: BigDecimal
)

data class RequestMenu(
    val menuName: String,
    val price: BigDecimal
)