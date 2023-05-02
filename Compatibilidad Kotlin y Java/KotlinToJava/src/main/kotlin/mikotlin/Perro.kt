package mikotlin

data class Perro(val id: Int = 0, var nombre: String, var edad: Int, var raza: Raza) {

    enum class Raza {
        HUSKY, PASTOR_ALEMAN, LABRADOR, PITBULL
    }
}