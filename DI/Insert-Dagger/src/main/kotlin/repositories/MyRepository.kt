package repositories

import services.DataBaseService
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyRepository @Inject constructor(
    private val myDataBase: DataBaseService
) : Repository {

    private val myUUID: UUID = UUID.randomUUID()

    init {
        println("Initializing Repository: $myUUID")
    }


    override fun findAll() {
        println("finding in Repository $myUUID ...")
        myDataBase.findAll()
    }

    override fun save() {
        println("saving in Repository $myUUID ...")
        myDataBase.save()
    }
}