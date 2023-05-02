package storage

import java.util.*
import javax.inject.Inject
import javax.inject.Named

// @Singleton -> No quiero que sea Singleton
@Named("MyStorageA")
class MyStorageA @Inject constructor() : Storage {
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