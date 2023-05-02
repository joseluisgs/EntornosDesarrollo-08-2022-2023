package services

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataBaseService @Inject constructor() {
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