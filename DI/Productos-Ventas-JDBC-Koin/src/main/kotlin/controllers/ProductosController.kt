package controllers

import exceptions.ProductoNoDisponibleException
import exceptions.ProductoNoEncontradoException
import models.Producto
import mu.KotlinLogging
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import repositories.productos.ProductosRepository
import services.storage.productos.ProductosStorageService
import validators.validar

private val logger = KotlinLogging.logger {}

@Singleton
class ProductosController(
    private val productosRepository: ProductosRepository,
    @Named("ProductosCsvService") // Usamos el nombre para diferenciar los servicios, al pertenecer a la misma interfaz
    private val importStorage: ProductosStorageService,
    @Named("ProductosJsonService") // Usamos el nombre para diferenciar los servicios, al pertenecer a la misma interfaz
    private val exportStorage: ProductosStorageService
) {

    fun findAll(): List<Producto> {
        logger.info { "findAll" }
        return productosRepository.findAll()
    }

    fun findAllByDisponible(disponible: Boolean = true): List<Producto> {
        logger.info { "findAll: $disponible" }
        return productosRepository.findAllByDisponible(disponible)
    }

    fun findById(id: Long): Producto {
        logger.info { "findById: $id" }
        return productosRepository.findById(id)
            ?: throw ProductoNoEncontradoException("Producto con $id no existe en almacenamiento")
    }

    fun findByNombre(nombre: String): List<Producto> {
        logger.info { "findByNombre: $nombre" }
        return productosRepository.findByNombre(nombre)
    }

    fun save(producto: Producto): Producto {
        logger.info { "save: $producto" }
        producto.validar() // Validar el producto
        return productosRepository.save(producto)
    }

    fun update(producto: Producto): Producto {
        logger.info { "update: $producto" }
        producto.validar() // Validar el producto
        return productosRepository.update(producto)
    }

    fun deleteById(id: Long): Producto {
        logger.info { "deleteById: $id" }
        val producto = findById(id)
        return if (productosRepository.deleteById(id)) {
            producto
        } else {
            throw ProductoNoDisponibleException("Producto con $id no se ha podido borrar")
        }
    }

    fun exportData() {
        logger.info { "Productos export to Storage" }
        val productos = productosRepository.findAll()
        exportStorage.saveAll(productos)
        logger.debug { "Number of Productos exported to Storage: ${productos.size}" }
    }

    fun importData() {
        logger.info { "Productos import from Storage" }
        val productos = importStorage.loadAll()
        logger.debug { "Number of Productos imported from Storage: ${productos.size}" }
        // borramos antes todos los productos
        // productosRepository.deleteAll() // Me borra ya los datos de la base de datos que tengo por script
        // los recorremos e insertamos en el almacén
        productos.forEach { producto ->
            productosRepository.save(producto)
        }
    }
}