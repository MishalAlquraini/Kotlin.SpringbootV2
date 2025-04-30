package com.coded.spring.ordering

import com.coded.spring.serverCache
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class MenuService(
    val menuRepository: MenuRepository
) {
    fun addMenu(request: RequestMenu): MenuEntity {
        val newMenu = MenuEntity(
            name = request.menuName,
            price = request.price
        )
        return menuRepository.save(newMenu)
    }

    fun listMenu(search: String?): List<Menu> {
        val cachedMenu = menuCache["Menu"]

        val menu = if (cachedMenu.isNullOrEmpty()) {
            println("no new meals")
            val freshMenu = menuRepository.findAll().map {
                Menu(
                    menuName = it.name,
                    price = it.price
                )
            }
            menuCache.put("Menu", freshMenu)
            freshMenu
        } else {
            cachedMenu
        }

        return if (!search.isNullOrBlank()) {
            menu.filter { it.menuName.trim().lowercase().contains(search.trim().lowercase()) }
        } else {
            menu
        }
    }
}


val menuCache = serverCache.getMap<String, List<Menu>>("Menu")


data class Menu(
    val menuName: String,
    val price: BigDecimal
)

data class RequestMenu(
    val menuName: String,
    val price: BigDecimal
)