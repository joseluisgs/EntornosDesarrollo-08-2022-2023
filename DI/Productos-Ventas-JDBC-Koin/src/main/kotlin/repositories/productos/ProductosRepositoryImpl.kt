package repositories.productos

import models.Producto
import mu.KotlinLogging
import org.koin.core.annotation.Singleton
import services.database.DataBaseManager
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

@Singleton
class ProductosRepositoryImpl(
    private val dataBaseManager: DataBaseManager
) : ProductosRepository {

    override fun findAll(): List<Producto> {
        logger.debug { "findAll" }
        val productos = mutableListOf<Producto>()
        dataBaseManager.use { db ->
            val sql = "SELECT * FROM productos"
            val result = db.select(sql)
            result?.let {
                while (it.next()) {
                    val producto = Producto(
                        id = it.getLong("id"),
                        nombre = it.getString("nombre"),
                        precio = it.getDouble("precio"),
                        cantidad = it.getInt("cantidad"),
                        createdAt = LocalDateTime.parse(it.getObject("created_at").toString()),
                        updatedAt = LocalDateTime.parse(it.getObject("updated_at").toString()),
                        disponible = it.getBoolean("disponible")
                    )
                    productos.add(producto)
                }
            }
        }
        return productos
    }


    override fun findById(id: Long): Producto? {
        logger.debug { "findById $id" }
        var producto: Producto? = null
        dataBaseManager.use { db ->
            val sql = "SELECT * FROM productos WHERE id = ?"
            val result = db.select(sql, id)
            // Recorremos los resultados
            result?.let {
                while (it.next()) {
                    producto = Producto(
                        id = it.getLong("id"),
                        nombre = it.getString("nombre"),
                        precio = it.getDouble("precio"),
                        cantidad = it.getInt("cantidad"),
                        createdAt = LocalDateTime.parse(it.getObject("created_at").toString()),
                        updatedAt = LocalDateTime.parse(it.getObject("updated_at").toString()),
                        disponible = it.getBoolean("disponible")
                    )
                }
            }
        }
        return producto
    }

    override fun findAllByDisponible(disponible: Boolean): List<Producto> {
        logger.debug { "findAllByDisponible: $disponible" }
        val productos = mutableListOf<Producto>()
        dataBaseManager.use { db ->
            val sql = "SELECT * FROM productos WHERE disponible = ?"
            val result = db.select(sql, disponible)
            result?.let {
                while (it.next()) {
                    val producto = Producto(
                        id = it.getLong("id"),
                        nombre = it.getString("nombre"),
                        precio = it.getDouble("precio"),
                        cantidad = it.getInt("cantidad"),
                        createdAt = LocalDateTime.parse(it.getObject("created_at").toString()),
                        updatedAt = LocalDateTime.parse(it.getObject("updated_at").toString()),
                        disponible = it.getBoolean("disponible")
                    )
                    productos.add(producto)
                }
            }
        }
        return productos
    }

    override fun findByNombre(nombre: String): List<Producto> {
        logger.debug { "findByNombre: $nombre" }
        return findAll().filter { it.nombre.lowercase().contains(nombre.lowercase()) }
    }

    override fun save(entity: Producto): Producto {
        logger.debug { "save $entity" }
        val createdTime = LocalDateTime.now()
        var myId = 0L
        dataBaseManager.use { db ->
            val sql = "INSERT INTO productos VALUES (null, ?, ?, ?, ?, ?, ?)"
            val result = db.insertAndGetKey(
                sql,
                entity.nombre,
                entity.precio,
                entity.cantidad,
                createdTime.toString(),
                createdTime.toString(),
                entity.disponible
            )
            myId = result?.getLong(1) ?: 0L
        }
        return entity.copy(id = myId, createdAt = createdTime, updatedAt = createdTime)
    }

    override fun update(entity: Producto): Producto {
        logger.debug { "update $entity" }
        val updatedTime = LocalDateTime.now()
        dataBaseManager.use { db ->
            val sql =
                "UPDATE productos SET nombre = ?, precio = ?, cantidad = ?, updated_at = ?, disponible = ? WHERE id = ?"
            val res = db.update(
                sql,
                entity.nombre,
                entity.precio,
                entity.cantidad,
                updatedTime.toString(),
                entity.disponible,
                entity.id
            )
        }
        return entity.copy(updatedAt = updatedTime)
    }

    override fun deleteById(id: Long): Boolean {
        logger.debug { "deleteById $id" }
        var result: Int
        dataBaseManager.use { db ->
            val sql = "DELETE FROM productos WHERE id = ?"
            result = db.delete(sql, id)
        }
        return result == 1
    }

    override fun deleteAll() {
        logger.debug { "deleteAll" }
        dataBaseManager.use { db ->
            val sql = "DELETE FROM productos"
            db.delete(sql)
        }
    }
}