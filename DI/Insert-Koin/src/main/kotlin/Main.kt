import controllers.MyController
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule

fun main() {
    // Cargamos Koin!!
    startKoin {
        printLogger()
        // Los modulos con los que vamos a trabajar
        //modules(myDependenciasA)
        defaultModule()
    }

    MyApp().run()
}

class MyApp : KoinComponent {
    // Inyectamos los controladores, gracias a mi factory con dependencias
    // private val myControllerA: MyController by lazy { ControllersFactory.getInstance() }
    // private val myControllerB: MyController = ControllersFactory.getInstance()

    // Ahora con Koin
    private val myControllerA: MyController by inject()
    private val myControllerB: MyController = get()

    fun run() {
        println("Controlador A")
        myControllerA.findAll()
        myControllerA.save()
        myControllerA.store()
        myControllerA.load()

        println("")
        println("Controlador B")
        myControllerB.findAll()
        myControllerB.save()
        myControllerB.store()
        myControllerB.load()

    }
}