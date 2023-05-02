package `09-CompatibilidadJava`

import mijava.MiClase

/**
 * Recuerda que en Kotlin tenemos tipos Nulos y No Nulos
 * Es por ello que si traemos una clase de Java, debemos
 * indicar que es Nula o No Nula, porque si no la marca como Tipo Plataforma
 * Los platorm types son los tipos que no son Nulos o No Nulos, es decir
 * que no sabemos si son Nulos o No Nulos y por ello Kotlin los marca como
 * Tipo Plataforma y relaja las restricciones de Nulabilidad que tiene
 * el lenguaje para que no tengamos que estar haciendo validaciones
 * de Nulabilidad en el código y por ello debes indicar que es Nulo o No Nulo
 * si no quieres que Kotlin marque el tipo como Tipo Plataforma o tener problemas
 * Lo mejor es que desde el código de Java usar las anotaciones @Nullable y @NotNull
 */

fun main() {
    val clase = MiClase()
    val mensaje = clase.stringNotNull // String !
    val mensaje2 = clase.stringNullable // String !
    val mensaje3 = clase.stringNotNullNotation // String
    val mensaje4 = clase.stringNullableWithNotation// String?

    println(mensaje)
    println(mensaje2)
    println(mensaje3)
    println(mensaje4)

    // Y que pasa??
    println(mensaje.length)
    println(mensaje2.length)
    println(mensaje3.length)
    println(mensaje4?.length)

}