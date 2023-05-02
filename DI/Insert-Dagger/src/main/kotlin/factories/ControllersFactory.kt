package factories

import controllers.MyController
import repositories.MyRepository
import services.DataBaseService
import storage.MyStorageA
import storage.MyStorageB

object ControllersFactory {
    fun getInstance(): MyController {
        return MyController(
            myRepository = MyRepository(
                myDataBase = DataBaseService()
            ),
            myStorageA = MyStorageA(),
            myStorageB = MyStorageB()
        )
    }
}