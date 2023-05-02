import mikotlin.Perro;
import mikotlin.SingletonKotlin;
import mikotlin.UtilsKotlin;


public class MainJava {
    public static void main(String[] args) {
        System.out.println("Hola Java");

        Perro perro = new Perro(1, "Firulais", 1, Perro.Raza.LABRADOR);
        System.out.println(perro);

        // System.out.println("Fecha actual: " + UtilsKt.getFechaActual()); // AL ponerle el Jvm: File
        System.out.println("Fecha actual: " + UtilsKotlin.getFechaActual()); // AL ponerle el Jvm: File

        // System.out.println(UtilsKt.toTitleCase("hola mundo"));
        System.out.println(UtilsKotlin.toTitleCase("hola mundo"));

        System.out.println(SingletonKotlin.INSTANCE.getNombre());

        System.out.println("Nombre: " + SingletonKotlin.getNombreCompleto()); // Le he puesto el JvmStatic


    }
}
