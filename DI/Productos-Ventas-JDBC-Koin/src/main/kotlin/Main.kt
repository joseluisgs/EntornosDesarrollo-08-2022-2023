@file:OptIn(ExperimentalStdlibApi::class)

import config.AppConfig
import controllers.ProductosController
import controllers.VentasController
import factories.productoRandom
import models.User
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.logger.slf4jLogger

fun main() {
    println("Hola Carro de Compra con Bases de Datos JDBC + Koin")

    // Cargo el contexto de Koin de DI
    startKoin {
        slf4jLogger() // Logger de Koin para ver lo que hace opcional
        defaultModule() // Módulo por defecto de Koin con las dependencias
    }

    // Inicio la aplicación
    App().start()

}

// Debe heredar de KoinComponent para poder usar inyección de dependencias
class App : KoinComponent {
    fun start() {
        // Leemos la configuración de la aplicación
        val appConfig: AppConfig by inject()
        println("APP_NAME: ${appConfig.APP_NAME}")

        // usamos los controlaores ya con las dependencias
        val productosController: ProductosController by inject()
        val ventasController: VentasController by inject()


        // importamos los productos del CSV
        productosController.importData()

        // Operamos con los productos

        println("Todos los productos")
        productosController.findAll().forEach { println(it.toLocalString()) }

        println()
        println("Info Producto con ID 1")
        productosController.findById(1).also { println(it.toLocalString()) }

        println()
        println("Ponemos nuevo nombre no disponible el producto con ID 1")
        val producto = productosController.findById(1)
        val updatedProducto = producto.copy(nombre = "Producto 1 Updated", disponible = false)
        productosController.update(updatedProducto).also { println(it.toLocalString()) }

        println()
        println("Insertamos un nuevo producto")
        val newProducto = productoRandom()
        productosController.save(newProducto).also { println(it.toLocalString()) }

        println()
        println("Borramos el producto con ID 1")
        productosController.deleteById(1).also { println(it.toLocalString()) }

        println("Todos los productos")
        productosController.findAll().forEach { println(it.toLocalString()) }

        // Exportamos los productos a JSON
        println()
        println("Exportamos los productos a JSON")
        productosController.exportData()


        // Vamos ahora con las ventas
        println()
        println("Venta con ID 1")
        val venta1 = ventasController.findById(1).also { println(it.toLocalString()) }

        // Sacamos una factura de la venta con ID 1
        println()
        println("Factura de la venta con ID 1")
        ventasController.exportInvoice(venta1)

        println()
        println("Insertar nueva Venta")
        val user = User(1, "Juan", "juan@juan.com", "123456")
        val items = mapOf(2L to 2, 3L to 2) // Daría 60€
        var venta2 = ventasController.save(user.id, items).also { println(it.toLocalString()) }

        println()
        println("Factura de la venta con ID 2")
        ventasController.exportInvoice(venta2)

        // Exportamos las ventas a JSON
        println()
        println("Exportamos los productos a JSON")
        ventasController.exportData()
    }
}



