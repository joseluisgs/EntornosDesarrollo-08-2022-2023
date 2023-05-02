package repositories

import org.koin.core.annotation.Single
import services.DataBaseService
import java.util.*

@Single
class MyRepository(
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