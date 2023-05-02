@file:OptIn(ExperimentalStdlibApi::class)

import di.DaggerDI
import factories.productoRandom
import models.User

fun main() {
    println("Hola Carro de Compra con Bases de Datos JDBC + Dagger")

    // Inicio la aplicación
    App().start()

}

// Debe heredar de KoinComponent para poder usar inyección de dependencias
class App {
    fun start() {
        // Cargamos el inyector!!!
        val appDI = DaggerDI.create()

        val appConfig = appDI.getAppConfig()
        println("APP_NAME: ${appConfig.APP_NAME}")


        // usamos los controlaores ya con las dependencias
        val productosController = appDI.getProductosController()
        val ventasController = appDI.getVentasController()


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



