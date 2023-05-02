package repositories.ventas

import models.LineaVenta
import models.Venta
import mu.KotlinLogging
import org.koin.core.annotation.Singleton
import services.database.DataBaseManager
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

@Singleton
class VentasRepositoryImpl(
    private val dataBaseManager: DataBaseManager
) : VentasRepository {

    override fun findAll(): List<Venta> {
        logger.debug { "findAll()" }
        val ventas = mutableListOf<Venta>()
        dataBaseManager.use { db ->
            val sql = "SELECT * FROM ventas"
            val rs = db.select(sql)
            rs?.let {
                while (rs.next()) {
                    ventas.add(
                        Venta(
                            id = rs.getLong("id"),
                            userId = rs.getLong("user_id"),
                            createdAt = LocalDateTime.parse(it.getObject("created_at").toString()),
                            updatedAt = LocalDateTime.parse(it.getObject("updated_at").toString()),
                        )
                    )
                }
            }
        }
        if (ventas.isNotEmpty()) {
            // Obtenemos las lineas de las ventas
            ventas.forEach { venta ->
                dataBaseManager.use { db ->
                    val sql = "SELECT * FROM lineas_ventas WHERE venta_id = ?"
                    val rs = db.select(sql, venta.id)
                    rs?.let {
                        while (rs.next()) {
                            venta.addLinea(
                                LineaVenta(
                                    ventaId = rs.getLong("venta_id"),
                                    lineaId = rs.getLong("linea_id"),
                                    productoId = rs.getLong("producto_id"),
                                    cantidad = rs.getInt("cantidad"),
                                    productoPrecio = rs.getDouble("producto_precio"),
                                )
                            )
                        }
                    }
                }
            }
        }
        return ventas
    }

    override fun findById(id: Long): Venta? {
        logger.debug { "findById(${id})" }
        var venta: Venta? = null

        // Obtenemos la venta de la base de datos
        dataBaseManager.use { db ->
            val sql = "SELECT * FROM ventas WHERE id = ?"
            val rs = db.select(sql, id)
            rs?.let {
                while (rs.next()) {
                    venta = Venta(
                        id = rs.getLong("id"),
                        userId = rs.getLong("user_id"),
                        createdAt = LocalDateTime.parse(it.getObject("created_at").toString()),
                        updatedAt = LocalDateTime.parse(it.getObject("updated_at").toString()),
                    )
                }
            }
        }
        if (venta != null) {
            // Obtenemos las lineas de la venta
            dataBaseManager.use { db ->
                val sql = "SELECT * FROM lineas_ventas WHERE venta_id = ?"
                val rs = db.select(sql, id)
                rs?.let {
                    while (rs.next()) {
                        venta?.addLinea(
                            LineaVenta(
                                ventaId = rs.getLong("venta_id"),
                                lineaId = rs.getLong("linea_id"),
                                productoId = rs.getLong("producto_id"),
                                cantidad = rs.getInt("cantidad"),
                                productoPrecio = rs.getDouble("producto_precio"),
                            )
                        )
                    }
                }
            }
        }
        return venta
    }

    override fun save(entity: Venta): Venta {
        logger.debug { "save: $entity" }
        // Lo trato todo como una transacción
        val createdTime = LocalDateTime.now()
        var myId = 0L
        dataBaseManager.use { db ->
            db.transaction {
                val sql = "INSERT INTO ventas VALUES (NULL, ?, ?, ?, ?, ?)"
                val rs = db.insertAndGetKey(
                    sql,
                    entity.userId,
                    createdTime,
                    createdTime,
                    entity.totalPrecio,
                    entity.totalItems
                )
                myId = rs?.getLong(1) ?: 0L
                // Ahora por cada linea de venta, la guardamos
                entity.lineas.forEach { linea ->
                    val sql = "INSERT INTO lineas_ventas VALUES (?, ?, ?, ?, ?, ?)"
                    db.insert(
                        sql,
                        myId,
                        linea.lineaId,
                        linea.productoId,
                        linea.cantidad,
                        linea.productoPrecio,
                        linea.totalPrecio
                    )
                }
            }
        }
        return entity.copy(
            id = myId,
            createdAt = createdTime,
            updatedAt = createdTime,
            lineas = entity.lineas.map { it.copy(ventaId = myId) }.toMutableList()
        )
    }

    override fun update(entity: Venta): Venta {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}