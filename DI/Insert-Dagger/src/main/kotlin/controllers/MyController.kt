package controllers


import repositories.Repository
import storage.Storage
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MyController @Inject constructor(
    private val myRepository: Repository,
    @Named("MyStorageA")
    private val myStorageA: Storage,
    @Named("MyStorageB")
    private val myStorageB: Storage,
) {

    private val myUUID: UUID = UUID.randomUUID()

    init {
        println("Initializing Controller: $myUUID")
    }


    fun findAll() {
        println("finding in Controller $myUUID ...")
        myRepository.findAll()
    }

    fun save() {
        println("saving in Controller $myUUID ...")
        myRepository.save()
    }

    fun store() {
        println("storing in Controller $myUUID ...")
        myStorageA.store()
        myStorageB.store()
    }

    fun load() {
        println("loading in Controller $myUUID ...")
        myStorageA.load()
        myStorageB.load()
    }
}