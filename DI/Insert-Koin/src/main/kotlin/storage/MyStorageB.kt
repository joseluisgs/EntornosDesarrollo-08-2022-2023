package storage

import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import java.util.*

@Single
@Named("MyStorageB")
class MyStorageB : Storage {
    private val myUUID: UUID = UUID.randomUUID()

    init {
        println("Initializing Storage B: $myUUID")
    }

    override fun store() {
        println("Saving to Storage B $myUUID...")
    }

    override fun load() {
        println("Loading from Storage B $myUUID...")
    }
}