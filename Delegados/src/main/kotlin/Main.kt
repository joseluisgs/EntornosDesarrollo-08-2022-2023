import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*
Los delegados en Kotlin permiten que una clase pueda delegar todos los elementos
que son de carácter público a un objeto.
 */

/**
 * Delegación es cuando dejamos a otra entidad que maneje una de nuestras propiedades
 * Lazy es cuando la propiedad se inicializa hasta que se la requiera
 * https://kotlinlang.org/docs/delegated-properties.html#lazy-properties
 *
 * Observable es cuando queremos que una propiedad se actualice cuando otra cambia realizamos una acciḉon
 * https://kotlinlang.org/docs/delegated-properties.html#observable-properties
 *
 * Vetoable cuando se actualiza una propiedad ejecuta una acción y podemos acotar el valor que se le asigna
 *
 * https://medium.com/backyard-programmers/kotlin-standard-delegates-lazy-observable-and-vetoable-761a82b74e57
 *
 * Puedo usar not null para que no me permita asignar null a una propiedad
 */

class Delegate {
    val lazyValue by lazy {
        // Solo se ejecuta la primera vez que se accede a la propiedad
        println("Ahora es cuando me creo que soy Lazy")
        "Hello"
    }

    // Patron observer!!
    var observableValue by Delegates.observable("Hello") { _, old, new ->
        // observamos el cambio de valor y ejecutamos una accion
        println("Se cambio $old -> $new")
    }

    var vetoableValue by Delegates.vetoable(0) { _, old, new ->
        println("Se cambio el valor de la propiedad de $old a $new")
        // Si el valor esta entre 1 y 10 lo aceptamos, si no lo rechazamos
        new in 1..10
    }

    // Not null
    var notNull by Delegates.notNull<String>()

    // O hacerme mi propio delegado, para ello debemos delegar en una clase que implemente la interfaz ReadWriteProperty
    // https://devexperto.com/delegado-propiedad-personalizado/

    var limitedInt by LimitedIntDelegate(0, 10)
}

class LimitedIntDelegate(private val min: Int, private val max: Int) : ReadWriteProperty<Any, Int> {
    private var value: Int? = null

    // El thisRef es el objeto que contiene la propiedad
    // El property es la propiedad que se esta delegando
    // El value es el valor que se le esta asignando a la propiedad
    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        // devolvemos el valor o el minimo
        return value ?: min
    }

    // El thisRef es el objeto que contiene la propiedad
    // El property es la propiedad que se esta delegando
    // El value es el valor que se le esta asignando a la propiedad
    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        // Si el valor esta entre el minimo y el maximo lo asignamos, si no asignamos el minimo o el maximo
        this.value = if (value in min..max) value else if (value < min) min else max
    }
}

fun main() {
    val delegate = Delegate()
    println(delegate.lazyValue)
    println(delegate.lazyValue)

    delegate.observableValue = "Hola"
    println(delegate.observableValue)
    delegate.observableValue = "Mundo"
    println(delegate.observableValue)

    delegate.vetoableValue = 5
    println(delegate.vetoableValue)
    delegate.vetoableValue = 15
    println(delegate.vetoableValue)
    delegate.vetoableValue = -5
    println(delegate.vetoableValue)
    delegate.vetoableValue = 25
    println(delegate.vetoableValue)

    delegate.notNull = "Hola"
    println(delegate.notNull)

    delegate.limitedInt = 5
    println(delegate.limitedInt)

    delegate.limitedInt = 15
    println(delegate.limitedInt)

    delegate.limitedInt = -5
    println(delegate.limitedInt)

}