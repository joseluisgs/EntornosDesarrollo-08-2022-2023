import di.DaggerDI

fun main() {
    MyApp().run()
}

class MyApp {
    // Inyectamos los controladores, gracias a mi factory con dependencias
    // private val myControllerA: MyController by lazy { ControllersFactory.getInstance() }
    // private val myControllerB: MyController = ControllersFactory.getInstance()

    // Ahora con Dagger
    // cargamos el inyector!!
    private val appDI = DaggerDI.create()!!
    private val myControllerA = appDI.getMyController()
    private val myControllerB by lazy { appDI.getMyController() }

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