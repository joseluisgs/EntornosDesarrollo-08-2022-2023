package mikotlin

object SingletonKotlin {
    val nombre = "Singleton Kotlin"

    @JvmStatic
    fun getNombreCompleto() = nombre
}