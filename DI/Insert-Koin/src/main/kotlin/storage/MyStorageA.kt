package storage

import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named
import java.util.*

@Factory
@Named("MyStorageA")
class MyStorageA : Storage {
    private val myUUID: UUID = UUID.randomUUID()

    init {
        println("Initializing Storage A: $myUUID")
    }

    override fun store() {
        println("Saving to Storage A $myUUID...")
    }

    override fun load() {
        println("Loading from Storage A $myUUID...")
    }
}