package anotaciones.component

import org.koin.core.annotation.Single
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject


@Single
class Termisifon : Bomba {
    val calentador: Calentador by inject(named("CalentadorElectrico"))
    override fun bombear() {
        if (calentador.estaCaliente()) {
            println("=> => bombeando => =>")
        }
    }
}