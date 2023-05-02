package services

import org.koin.core.annotation.Single
import java.util.*

@Single
class DataBaseService {
    private val myUUID: UUID = UUID.randomUUID()

    init {
        println("Initializing DataBase: $myUUID")
    }

    fun findAll() {
        println("finding in DataBase $myUUID ...")
    }

    fun save() {
        println("saving in DataBase $myUUID ...")
    }
}