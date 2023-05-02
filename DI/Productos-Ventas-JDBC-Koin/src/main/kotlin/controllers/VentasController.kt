package controllers

import exceptions.CarritoNoValidoException
import exceptions.ProductoNoEncontradoException
import exceptions.ProductoNoValidoException
import models.LineaVenta
import models.Venta
import mu.KotlinLogging
import org.koin.core.annotation.Singleton
import repositories.productos.ProductosRepository
import repositories.ventas.VentasRepository
import services.storage.ventas.VentasStorageService
import validators.validar

private val logger = KotlinLogging.logger {}

@Singleton
class VentasController(
    private val ventasRepository: VentasRepository,
    private val productosRepository: ProductosRepository,
    private val exportStorage: VentasStorageService
) {
    fun save(userId: Long, items: Map<Long, Int>): Venta {
        logger.info { "save: $userId, $items" }
        require(items.isNotEmpty()) { throw CarritoNoValidoException("Carrito debe tener al menos un producto") }

        val venta = Venta(id = 0, userId = userId)
        // Validar
        venta.validar()
        comprobarExistenciaProductos(items)
        // Creamos las lineas de carrito
        crearLineasCarrito(venta, items)
        // añadir lineas al carrito
        // actualizar stock
        actualizarStock(items)
        // guardar venta
        return ventasRepository.save(venta)

    }

    private fun crearLineasCarrito(venta: Venta, items: Map<Long, Int>) {
        logger.debug { "crearLineasCarrito: $venta, $items" }
        items.forEach { item ->
            val producto = productosRepository.findById(item.key)
                ?: throw ProductoNoValidoException("Producto con id ${item.key} no es válido")
            logger.debug { "Producto encontrado: $producto" }
            val linea = LineaVenta(
                ventaId = venta.id,
                lineaId = venta.nextLineaId,
                productoId = producto.id,
                cantidad = item.value,
                productoPrecio = producto.precio
            )
            venta.addLinea(linea)
        }
    }

    private fun actualizarStock(items: Map<Long, Int>) {
        items.forEach { item ->
            val producto = productosRepository.findById(item.key)
                ?: throw ProductoNoValidoException("Producto con id ${item.key} no es válido")
            logger.debug { "Producto encontrado: $producto" }
            val updated = producto.copy(cantidad = producto.cantidad - item.value)
            productosRepository.update(updated)
        }
    }

    private fun comprobarExistenciaProductos(items: Map<Long, Int>) {
        logger.debug { "comprobarExistenciaProductos: $items" }
        items.forEach { item ->
            val producto = productosRepository.findById(item.key)
                ?: throw ProductoNoEncontradoException("Producto con id ${item.key} no existe")
            logger.debug { "Producto encontrado: $producto" }
            if (producto.cantidad < item.value) {
                throw CarritoNoValidoException("No hay suficiente stock para el producto ${producto.id}")
            }

        }
    }

    fun findById(id: Long): Venta {
        logger.info { "findById: $id" }
        return ventasRepository.findById(id)
            ?: throw Exception("Carrito con $id no existe en almacenamiento")
    }

    fun exportData() {
        logger.info { "Ventas export to Storage" }
        val ventas = ventasRepository.findAll()
        exportStorage.saveAll(ventas)
        logger.debug { "Number of Ventas exported to Storage: ${ventas.size}" }
    }

    fun exportInvoice(venta: Venta) {
        logger.info { "saveInvoice: $venta" }
        exportStorage.saveVenta(venta)
    }
}
