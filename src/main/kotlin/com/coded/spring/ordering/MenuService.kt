package com.coded.spring.ordering

import com.coded.spring.serverCache
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

    fun listMenu(): List<Menu>{
        val cachedMenu = menuCache["Menu"]
        if (cachedMenu?.size == 0 || cachedMenu == null){
            println("no new meals")
            val menu = menuRepository.findAll().map {
        Menu(
            menuName = it.name,
            price = it.price
        ) }
            menuCache.put("Menu", menu)
            return menu
        }
        return cachedMenu
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