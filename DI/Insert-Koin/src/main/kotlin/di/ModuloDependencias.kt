package di

import controllers.MyController
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import repositories.MyRepository
import repositories.Repository
import services.DataBaseService
import storage.MyStorageA
import storage.MyStorageB
import storage.Storage

val myDependenciasA = module {
    // Definimos las dependencias de la forma que queramos!!!

    // Como singleton, siempre se crea una sola instancia
    single<Repository> { DataBaseService() }

    // Como factory se crea una instancia nueva cada vez que se pide
    factory<Storage>(named("StorageA")) { MyStorageA() } // Debemos hacerle el binding con la interfaz
    single<Storage>(named("StorageB")) { MyStorageB() }


    single<Repository> { MyRepository(get()) }

    single { MyController(get(), get(named("StorageA")), get(named("StorageB"))) }

    single(named("ControladorTodoA")) { MyController(get(), get(named("StorageA")), get(named("StorageA"))) }
}

val myDependenciasB = module {
    // Definimos las dependencias de la forma que queramos!!!

    // Como singleton, siempre se crea una sola instancia
    singleOf(::DataBaseService) {
        bind<Repository>()
    }

    // Como factory se crea una instancia nueva cada vez que se pide
    factoryOf(::MyStorageA) {
        bind<Storage>()
        named("StorageA")
    }

    singleOf(::MyStorageB) {
        bind<Storage>()
        named("StorageB")
    }

    singleOf(::MyRepository) {
        bind<Repository>()
    }

    singleOf(::MyController)
}