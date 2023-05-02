package mijava;

public class SingletonJava {
    private static SingletonJava instance = null;
    private String nombre;

    private SingletonJava() {
    }

    public static SingletonJava getInstance() {
        if (instance == null) {
            instance = new SingletonJava();
        }
        return instance;
    }

    public String getNombre() {
        return nombre;
    }
}
