package koin.casas

import casas.CasasModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

// Componente de Koin
class CasasApp : KoinComponent {
    // Obtenemos casa de manera perezosa
    val casa: Casa by inject()
    // Obtenemos casa de manera eagerly
    // val casa: Casa = get()

    fun run() {
        println("Ejemplo de Casas Koin")
        println("=======================")
        println("Casa: $casa")
        casa.entrar()
        casa.ventilar()
        println()

        val casa2: Casa by inject()
        println("Casa2: $casa2")

    }
}

fun main() {
    // Comenzamos con Koin
    startKoin {
        // use Koin logger
        printLogger()
        // declare modules
        modules(CasasModule)
    }
    CasasApp().run()
}