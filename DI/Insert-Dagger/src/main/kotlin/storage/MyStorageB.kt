package storage

import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Named("MyStorageB")
class MyStorageB @Inject constructor() : Storage {
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