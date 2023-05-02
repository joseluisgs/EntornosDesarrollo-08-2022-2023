import mijava.DateUtils
import mijava.Estudios
import mijava.Persona
import mijava.SingletonJava

fun main(args: Array<String>) {
    println("Hola Kotlin!")

    val persona = Persona(1, "Juan", "Leganés", Estudios.DAM)
    println(persona)

    val nombre = persona.nombre

    println(nombre)

    println(SingletonJava.getInstance().nombre)

    println(DateUtils.getFechaActual())
}