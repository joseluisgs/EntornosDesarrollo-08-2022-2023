package casas

import koin.casas.Casa
import koin.casas.Puerta
import koin.casas.Ventana
import org.koin.dsl.module

// Modulo que indica cómo son las dependencias.
val CasasModule = module {
    // Puertas y ventanas como instancias nuevas, para eso usamos factory
    factory { Puerta() }
    factory { Ventana() }
    // Creamos la casa como Singleton
    single { Casa(get(), get()) }
}